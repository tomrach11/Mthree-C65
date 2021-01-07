import React from 'react';

function ContactCard(props) {
  return (
    <div>
      <h2>Name: {props.Name}</h2>
      <h2>Phone: {props.Phone}</h2>
      <h2>Email: {props.Email}</h2>
    </div>
  )
}

export default ContactCard;