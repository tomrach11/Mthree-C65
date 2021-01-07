import React, { useState } from 'react';

function Timer() {

  let [time, setTime] = useState(25);
  // let [isPause, setIsPause] = useState(true);

  let decrementTimer = () => {
      setTime(time - 1);
  }

  return (
    <div>
      <h1>{ time }</h1>
      <button onClick = { () => setInterval(decrementTimer(), 1000) }> Start </button>
      <button onClick = { () => clearInterval() }> Stop </button>

    </div>
  )
}

export default Timer;

