import './App.css';
import TodoList from './features/TodoList';

function App() {
  // useEffect(() => {
  //   const fetchTodos = async () => {
  //     const params = {
  //       status: true
  //     }
  //     let todoList = await todoApi.getTodos(params);
  //     console.log(todoList);
  //   }

  //   fetchTodos();
  // }, [])
  return (
    <div className="App">
      <TodoList />
    </div>
  );
}

export default App;
