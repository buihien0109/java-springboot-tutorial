// Truy cập vào các thành phần
const courseListEl = document.querySelector(".course-list");
const totalMoneyEl = document.getElementById("total-money");


// Hiển thị thông tin item trong cart
const renderItem = () => {
    // Lấy danh sách item trong cart
    let items = getItemsOfCart() || [];

    // Update số lượng cart
    updateTotalCart();

    courseListEl.innerHTML = "";

    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        courseListEl.innerHTML += `
            <div class="course-item d-flex border mb-4">
                <div class="image">
                    <img src="${item.image}" alt="${item.title}">
                </div>
                <div class="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                    <div>
                        <div class="d-flex justify-content-between align-items-center">
                            <h2 class="text-dark fs-5 fw-normal">${item.title}</h2>
                            <h2 class="text-danger fs-5 fw-normal">${formatMoney(item.price)}</h2>
                        </div>
                        <p class="text-black-50">
                            Hình thức học :
                            <span class="fw-bold">${item.type}</span>
                        </p>
                    </div>
                    <div>
                        <button class="text-primary border-0 bg-transparent fw-light" onclick="removeItem(${item.id})">
                            <span><i class="fa-solid fa-trash-can"></i></span>
                            Xóa
                        </button>
                    </div>
                </div>
            </div>
        `
    }

    updateTotalMoney(items)
}

// Xóa sản phẩm trong giỏ hàng
const removeItem = id => {
    removeItemFromCart(id);
    renderItem();
}

// Update tổng số tiền
const updateTotalMoney = (arr) => {
    let total = 0;
    for (let i = 0; i < arr.length; i++) {
        total += arr[i].price;
    }
    
    totalMoneyEl.innerHTML = formatMoney(total);
}

// Format tiền
const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

renderItem()