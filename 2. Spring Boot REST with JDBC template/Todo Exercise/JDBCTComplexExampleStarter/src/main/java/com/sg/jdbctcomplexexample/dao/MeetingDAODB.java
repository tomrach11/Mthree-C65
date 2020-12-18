package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class MeetingDAODB implements MeetingDao {
    @Autowired
    JdbcTemplate jdbc;


    @Override
    public List<Meeting> getAllMeetings() {
        final String SELECT_ALL_MEETINGS = "SELECT * FROM meeting";
        List<Meeting> meetings = jdbc.query(SELECT_ALL_MEETINGS, new MeetingMapper());

        //add room and employee to meeting
        addRoomAndEmployeesToMeetings(meetings);

        return meetings;
    }

    @Override
    public Meeting getMeetingByid(int id) {
        try {
            final String SELECT_MEETING_BY_ID = "SELECT * FROM meeting WHERE id = ?";
            Meeting meeting = jdbc.queryForObject(SELECT_MEETING_BY_ID,
                    new MeetingMapper(), id);
            //using helper methods to fill room object and list of employee objects
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
            return meeting;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    //using 2 other helper method below
    private void addRoomAndEmployeesToMeetings(List<Meeting> meetings) {
        //loop through list of meeting and fill each with room object and list of employees
        for(Meeting meeting : meetings) {
            //get room object from helper method 1
            meeting.setRoom(getRoomForMeeting(meeting));
            //get list of employee from helper method 2
            meeting.setAttendees(getEmployeesForMeeting(meeting));
        }
    }

    @Override
    @Transactional
    public Meeting addMeeting(Meeting meeting) {
        final String INSERT_MEETING = "INSERT INTO meeting(name, time, roomId) VALUES(?,?,?)";
        //add to meeting table
        jdbc.update(INSERT_MEETING,
                meeting.getName(),
                Timestamp.valueOf(meeting.getTime()),
                meeting.getRoom().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        meeting.setId(newId);

        //add to breach table
        insertMeetingEmployee(meeting);

        return meeting;
    }
    //helper method to add to breach table
    private void insertMeetingEmployee(Meeting meeting) {
        final String INSERT_MEETING_EMPLOYEE = "INSERT INTO meeting_employee"
                + "(meetingId, employeeId) VALUES(?,?)";
        //loop through meetings to insert each employee in breach table
        for (Employee employee : meeting.getAttendees()) {
            //employee id is from list of employees in meeting object
            jdbc.update(INSERT_MEETING_EMPLOYEE, meeting.getId(), employee.getId());
        }
    }

    @Override
    @Transactional
    public void updateMeeting(Meeting meeting) {
        final String UPDATE_MEETING = "UPDATE meeting "
                + "SET name = ?, time = ?, roomId = ? WHERE id = ?";
        //update meeting in meeting table
        jdbc.update(UPDATE_MEETING,
                meeting.getName(),
                Timestamp.valueOf(meeting.getTime()),
                meeting.getRoom().getId(),
                meeting.getId());

        //delete employees associate with this meeting from breach table
        //in order to add new list from the meeting object passed from argument
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, meeting.getId());

        //add employees to breach table with updated data after delete them out
        insertMeetingEmployee(meeting);
    }

    @Override
    public void deleteMeetingById(int id) {
        //delete from associate tables
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);

        //delete from the meeting table
        final String DELETE_MEETING = "DELETE FROM meeting WHERE id = ?";
        jdbc.update(DELETE_MEETING, id);
    }

    @Override
    public List<Meeting> getMeetingsForRoom(Room room) {
        final String SELECT_MEETINGS_FOR_ROOM = "SELECT * FROM meeting WHERE roomId = ?";
        //get all meetings associate with the room
        List<Meeting> meetings = jdbc.query(SELECT_MEETINGS_FOR_ROOM,
                new MeetingMapper(), room.getId());

        //fill out room and list of employees in meetings to complete creating meeting objects
        addRoomAndEmployeesToMeetings(meetings);

        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsForEmployee(Employee employee) {
        final String SELECT_MEETINGS_FOR_EMPLOYEE = "SELECT * FROM meeting m "
                + "JOIN meeting_employee me ON m.id = me.meetingId WHERE me.employeeId = ?";
        //get all meeting associate with the employee
        List<Meeting> meetings = jdbc.query(SELECT_MEETINGS_FOR_EMPLOYEE,
                new MeetingMapper(), employee.getId());

        //fill out room and list of employees in meetings to complete creating meeting objects
        addRoomAndEmployeesToMeetings(meetings);

        return meetings;
    }

    public static final class MeetingMapper implements RowMapper<Meeting> {

        @Override
        public Meeting mapRow(ResultSet rs, int index) throws SQLException {
            Meeting meet = new Meeting();
            meet.setId(rs.getInt("id"));
            meet.setName(rs.getString("name"));
            meet.setTime(rs.getTimestamp("time").toLocalDateTime());
            return meet;
        }
    }

    //helper method 1
    private Room getRoomForMeeting(Meeting meeting) {
        final String SELECT_ROOM_FOR_MEETING = "SELECT r.* FROM room r "
                + "JOIN meeting m ON r.id = m.roomId WHERE m.id = ?";
        //return room object and using RoomMapper from RoomDAODB to convert sql to room object
        return jdbc.queryForObject(SELECT_ROOM_FOR_MEETING, new RoomDAODB.RoomMapper(),
                meeting.getId());
    }

    //helper method 2
    private List<Employee> getEmployeesForMeeting(Meeting meeting) {
        final String SELECT_EMPLOYEES_FOR_MEETING = "SELECT e.* FROM employee e "
                + "JOIN meeting_employee me ON e.id = me.employeeId WHERE me.meetingId = ?";
        //return list of employees using EmployeeMapper from EmployeeDAODB to convert sql to employee object
        return jdbc.query(SELECT_EMPLOYEES_FOR_MEETING, new EmployeeDAODB.EmployeeMapper(),
                meeting.getId());
    }
}
