const menuEls = document.querySelectorAll(".nav-treeview > .nav-item > .nav-link");

const activeMenu = () => {
    let path = window.location.pathname;

    Array.from(menuEls).map(e => e.classList.remove("active"));
    Array.from(menuEls).map(e => e.parentElement.parentElement.previousElementSibling.classList.remove("active"));

    menuEls.forEach(e => {
        if(e.getAttribute("href") === path) {
            e.classList.add("active");
            e.parentElement.parentElement.previousElementSibling.classList.add("active");
            e.parentElement.parentElement.previousElementSibling.parentElement.classList.add("menu-open");
        }
    })
}

activeMenu();