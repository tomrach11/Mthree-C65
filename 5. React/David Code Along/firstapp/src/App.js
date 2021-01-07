import './App.css';
import Counter from './components/Counter';
import CounterWithHooks from './components/CounterWithHooks';

function App() {
  return (
    <div className="App">
      <h1>Hello World</h1>
        <CounterWithHooks />
    </div>
  );
}

export default App;
