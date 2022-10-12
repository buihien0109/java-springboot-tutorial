import React from "react";

function TodoFilter(props) {
    const { onFilterTodo } = props;
    return (
        <div>
            <button onClick={() => onFilterTodo("")}>All</button>
            <button onClick={() => onFilterTodo("true")}>Active</button>
            <button onClick={() => onFilterTodo("false")}>UnActive</button>
        </div>
    );
}

export default TodoFilter;
