import React, { useState } from "react";

function TodoInput(props) {
    const { onAddTodo } = props;
    const [title, setTitle] = useState("");

    const handleAddTodo = () => {
        onAddTodo(title);
        setTitle("");
    };

    return (
        <>
            <input
                type="text"
                placeholder="Enter todo ..."
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />
            <button onClick={handleAddTodo}>Add</button>
        </>
    );
}

export default TodoInput;
