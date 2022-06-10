// Thêm id vào cart
const addItemToCart = item => {
    // Kiểm tra xem user đã đăng nhập hay chưa
    let user = getFromLocalStorage("userLogin");
    if(user) {
        let cartUser = getFromLocalStorage("cartUser");
        if(!cartUser) {
            cartUser = [
                {
                    userId : user.id,
                    items : [item]
                }
            ]
        } else {
            let cartOfUser = cartUser.find(c => c.userId == user.id);
            let isExist = cartOfUser.items.some(i => i.id == item.id);
            if(isExist) {
                alert("Sản phẩm đã ở trong giỏ hàng");
                return false;
            }
            cartOfUser.items.push(item);
        }
        addToLocalStorage("cartUser", cartUser)
    } else {
        let cartAnonymous = getFromLocalStorage("cartAnonymous");
        if(!cartAnonymous) {
            cartAnonymous = [item];
        } else {
            let isExist = cartAnonymous.some(i => i.id == item.id);
            if(isExist) {
                alert("Sản phẩm đã ở trong giỏ hàng");
                return false;
            }
            cartAnonymous.push(item)
        }
        addToLocalStorage("cartAnonymous", cartAnonymous)
    }
    updateTotalCart();
    return true;
}

const removeItemFromCart = itemId => {
    // Kiểm tra xem user đã đăng nhập hay chưa
    let user = getFromLocalStorage("userLogin");
    if(user) {
        let cartUser = getFromLocalStorage("cartUser");
        let cartOfUser = cartUser.find(c => c.userId == user.id);
        cartOfUser.items = cartOfUser.items.filter(i => i.id != itemId);
        addToLocalStorage("cartUser", cartUser)
    } else {
        let cartAnonymous = getFromLocalStorage("cartAnonymous");
        cartAnonymous = cartAnonymous.filter(i => i.id != itemId);
        addToLocalStorage("cartAnonymous", cartAnonymous)
    }
    updateTotalCart();
}

const removeAllItem = () => {
    // Kiểm tra xem user đã đăng nhập hay chưa
    let user = getFromLocalStorage("userLogin");
    if(user) {
        let cartUser = getFromLocalStorage("cartUser");
        let cartOfUser = cartUser.find(c => c.userId == user.id);
        cartOfUser.items = [];
        addToLocalStorage("cartUser", cartUser)
    } else {
        let cartAnonymous = getFromLocalStorage("cartAnonymous");
        cartAnonymous = [];
        addToLocalStorage("cartAnonymous", cartAnonymous)
    }
    updateTotalCart();
}

const getItemsOfCart = () => {
    // Kiểm tra xem user đã đăng nhập hay chưa
    let user = getFromLocalStorage("userLogin");

    if(user) {
        let cartUser = getFromLocalStorage("cartUser");
        let cartOfUser = cartUser.find(c => c.userId == user.id);
        return cartOfUser.items;
    }
    return getFromLocalStorage("cartAnonymous");
}

const updateTotalCart = () => {
    let items = getItemsOfCart();
    document.querySelector(".cart-count").innerText = items.length;
}

updateTotalCart();