const params = new URLSearchParams(window.location.search);
const currentPage = Number(params.get("page")) || 1;
const id = params.get("id");

export { currentPage, id };