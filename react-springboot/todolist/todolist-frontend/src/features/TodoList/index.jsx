import { useEffect, useState } from "react";
import todoApi from "../../api/todoApi";
import TodoFilter from "./components/TodoFilter";
import TodoInput from "./components/TodoInput";
import TodoItem from "./components/TodoItem";

function TodoList() {
    const [todos, setTodos] = useState([]);
    const [status, setStatus] = useState("");

    useEffect(() => {
        const fetchTodos = async () => {
            const params = status ? { status } : null;
            const todoList = await todoApi.getTodos(params);
            setTodos(todoList);
        };

        fetchTodos();
    }, [status]);

    // Lọc công việc theo trạng thái
    const handleFilterTodo = async (status) => {
        setStatus(status);
    };

    // Xóa công việc
    const handleDeleteTodo = async (id) => {
        await todoApi.deleteTodo(id);
        let newTodos = todos.filter((todo) => todo.id !== id);
        setTodos(newTodos);
    };

    // Chỉnh sửa tiêu đề công việc
    const handleEditTodo = async (id) => {
        let updatedTodo = todos.find((todo) => todo.id === id);

        let title = window.prompt("Cập nhật công việc", updatedTodo.title);

        if (title === null) return;
        if (title.length === 0) {
            alert("Tiêu đề không được để trống");
            return;
        }

        const updatedTodoRequest = {
            title,
            status: updatedTodo.status,
        };

        let updatedTodoResponse = await todoApi.updateTodo(
            id,
            updatedTodoRequest
        );

        let newTodos = todos.map((todo) => {
            if (todo.id === id) {
                return updatedTodoResponse;
            }
            return todo;
        });
        setTodos(newTodos);
    };

    // Thêm công việc
    const handleAddTodo = async (title) => {
        if (title.length === 0) {
            alert("Tiêu đề không được để trống");
            return;
        }

        let newTodo = {
            title: title,
        };

        let todoRes = await todoApi.createTodo(newTodo);
        setTodos([...todos, todoRes]);
    };

    // Thay đổi trạng thái công việc
    const handleToggleStatus = async (id) => {
        let updatedTodo = todos.find((todo) => todo.id === id);

        const updatedTodoRequest = {
            title: updatedTodo.title,
            status: !updatedTodo.status,
        };

        let updatedTodoResponse = await todoApi.updateTodo(
            id,
            updatedTodoRequest
        );

        let newTodos = todos.map((todo) => {
            if (todo.id == id) {
                return updatedTodoResponse;
            }
            return todo;
        });
        setTodos(newTodos);
    };

    // Xóa tất cả công việc
    const clearAll = () => {
        setTodos([]);
    };

    return (
        <div className="todo-container">
            <h2>TodoList App</h2>
            <TodoInput onAddTodo={handleAddTodo} />

            {todos.length > 0 && (
                <>
                    <ul>
                        {todos.map((todo) => (
                            <TodoItem
                                key={todo.id}
                                {...todo}
                                onDeleteTodo={handleDeleteTodo}
                                onEditTodo={handleEditTodo}
                                onToggleStatus={handleToggleStatus}
                            />
                        ))}
                    </ul>

                    <TodoFilter onFilterTodo={handleFilterTodo} />

                    <button onClick={clearAll}>Clear All</button>
                </>
            )}
        </div>
    );
}

export default TodoList;
