import React, { useState } from 'react';

function CounterWithHooks() {

  const [count, setCount] = useState(1);

  const incrementCounter = () => {
    setCount(count + 1);
  }

  return (
    <div>
      <h2>{count}</h2>
      <button onClick={ () => incrementCounter() }>Increment One</button>
    </div>
  )
}

export default CounterWithHooks;