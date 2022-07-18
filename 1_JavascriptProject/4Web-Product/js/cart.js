// Lưu dữ liệu vào LocalStorage
const addToLocalStorage = arr => {
    localStorage.setItem("cart", JSON.stringify(arr));
    updateTotalProduct();
}

// Lấy dữ liệu từ LocalStorage và hiển thị
const getFromLocalStorage = (key) => {
    const valueLocalStorage = localStorage.getItem(key);
    if (valueLocalStorage) {
        return JSON.parse(valueLocalStorage);
    }
    return [];
}

// Cấu trúc của item trong cart
// const cart = [
//     {
//         id : 1 
//         name : 1 Lorem ipsum dolor sit amet
//         price : 500000
//         image : https://images.unsplash.com/photo-1523381210434-271e8be1f52b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60
//         size : "M"
//         count : 3
//     }
// ];

// Thêm sản phẩm vào giỏ hàng
const addItemToCart = item => {
    // Lấy dữ liệu từ localStorage
    let cart = getFromLocalStorage("cart");
    if (cart.length == 0) {
        cart.push(item)
    } else {
        // Tìm xem sản phẩm đã có trong cart hay chưa
        // Nếu chưa có -> thêm vào giỏ hàng
        let product = cart.find(p => p.id == item.id && p.size == item.size);
        if (!product) {
            cart.push(item);
        } else {
            product.count += item.count;
        }
    }

    // Lưu dữ liệu vào localStorage
    addToLocalStorage(cart);
}

// Hiển thị số lượng sản phẩm trong giỏ hàng
const updateTotalProduct = () => {
    let items = getFromLocalStorage("cart");
    document.querySelector(".cart-count").innerText = items.length;
}

// Format tiền
const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

updateTotalProduct();