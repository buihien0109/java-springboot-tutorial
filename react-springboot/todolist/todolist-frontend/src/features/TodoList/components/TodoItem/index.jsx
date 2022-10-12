import { useState } from 'react'

function TodoItem(props) {
    const { id, title, status, onDeleteTodo, onEditTodo, onToggleStatus } =
        props;
    const [checked, setChecked] = useState(status);

    const handleDeleteTodo = (id) => {
        onDeleteTodo(id);
    };

    const handleEditTodo = (id) => {
        onEditTodo(id);
    };

    const handleToggleStatus = (id) => {
        setChecked(!checked);
        onToggleStatus(id);
    };

    return (
        <li>
            <input
                type="checkbox"
                checked={checked}
                onChange={() => handleToggleStatus(id)}
            />
            <span className={status ? "active" : ""}>{title}</span>
            <button onClick={() => handleEditTodo(id)}>Edit</button>
            <button onClick={() => handleDeleteTodo(id)}>Delete</button>
        </li>
    );
}

export default TodoItem