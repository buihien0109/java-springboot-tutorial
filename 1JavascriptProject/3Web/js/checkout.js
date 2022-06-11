// Truy cập vào các thành phần
const nameEl = document.getElementById("name");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const inputsEl = document.querySelectorAll(".account-info input");
const orderItemsEl = document.querySelector(".order-items");
const totalMoneyEl = document.getElementById("total-money");
const btnSubmit = document.getElementById("btn-submit");

// Hiển thị thông tin của user nếu user đó đã đăng nhập
const displayInfoUser = () => {
    let user = getFromLocalStorage("userLogin");
    if(user) {
        nameEl.value = user.name;
        emailEl.value = user.email;
        phoneEl.value = user.phone;
        Array.from(inputsEl).map(i => i.disabled = true)
    }
}

// Hiển thị danh sách item + giá
const displayItem = () => {
    let items = getItemsOfCart();
    
    orderItemsEl.innerHTML = "";

    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        orderItemsEl.innerHTML += `
            <div class="order-item d-flex justify-content-between align-items-center">
                <p class="text-black-50">
                    ${item.title}
                </p>
                <p class="fw-bold">${formatMoney(item.price)}</p>
            </div>
        `
    }

    updateTotalMoney(items);
}

// Update tổng số tiền
const updateTotalMoney = (arr) => {
    let total = 0;
    for (let i = 0; i < arr.length; i++) {
        total += arr[i].price;
    }
    
    totalMoneyEl.innerHTML = formatMoney(total);
}

// Gửi đăng ký
btnSubmit.addEventListener("click", function() {
    removeAllItem();
    alert("Gửi đăng ký thành công");

    setTimeout(function() {
        window.location.href = "/";
    }, 1500);
})

// Format tiền
const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

displayInfoUser();
displayItem();