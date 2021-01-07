import React, { Component } from 'react'

class Counter extends Component {
  constructor() {
    super(); 
    this.state = {
      count: 0
    }
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    this.setState(prevState => {
      return { count: prevState.count + 1 }
    })
  }
  
  render() {
    return (
      <div>
        <h2>{this.state.count}</h2>
        <button onClick = {this.handleClick}>Add One</button>
      </div>
    )
  }
}
export default Counter;