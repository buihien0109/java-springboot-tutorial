let products = [
    {
        id : 1,
        name : "1 Lorem ipsum dolor sit amet",
        description : "1 Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus, dignissimos? Exercitationem iure architecto temporibus aliquam, numquam, culpa aperiam placeat voluptatem tempora nemo alias? Similique, rerum eius. Et provident voluptatem tenetur",
        images : [
            "https://images.unsplash.com/photo-1523381210434-271e8be1f52b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1543087903-1ac2ec7aa8c5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1544441893-675973e31985?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60"
        ],
        rating : 4.5,
        price : 400000,
        sizes : ["M", "L", "XL"]
    },
    {
        id : 2,
        name : "2 Lorem ipsum dolor sit amet",
        description : "2 Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus, dignissimos? Exercitationem iure architecto temporibus aliquam, numquam, culpa aperiam placeat voluptatem tempora nemo alias? Similique, rerum eius. Et provident voluptatem tenetur",
        images : [
            "https://images.unsplash.com/photo-1543087903-1ac2ec7aa8c5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1544441893-675973e31985?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60"
        ],
        rating : 4.6,
        price : 500000,
        sizes : ["M", "L"]
    },
    {
        id : 3,
        name : "3 Lorem ipsum dolor sit amet",
        description : "3 Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus, dignissimos? Exercitationem iure architecto temporibus aliquam, numquam, culpa aperiam placeat voluptatem tempora nemo alias? Similique, rerum eius. Et provident voluptatem tenetur",
        images : [
            "https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1544441893-675973e31985?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60"
        ],
        rating : 4.3,
        price : 550000,
        sizes : ["S", "M", "L"]
    },
    {
        id : 4,
        name : "4 Lorem ipsum dolor sit amet",
        description : "4 Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus, dignissimos? Exercitationem iure architecto temporibus aliquam, numquam, culpa aperiam placeat voluptatem tempora nemo alias? Similique, rerum eius. Et provident voluptatem tenetur",
        images : [
            "https://images.unsplash.com/photo-1544441893-675973e31985?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1543087903-1ac2ec7aa8c5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2xvdGhpbmd8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60"
        ],
        rating : 4.6,
        price : 300000,
        sizes : ["L", "XL", "XXL"]
    },
    {
        id : 5,
        name : "5 Lorem ipsum dolor sit amet",
        description : "5 Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus, dignissimos? Exercitationem iure architecto temporibus aliquam, numquam, culpa aperiam placeat voluptatem tempora nemo alias? Similique, rerum eius. Et provident voluptatem tenetur",
        images : [
            "https://images.unsplash.com/photo-1479064555552-3ef4979f8908?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1567401893414-76b7b1e5a7a5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1556905055-8f358a7a47b2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60"
        ],
        rating : 4.4,
        price : 600000,
        sizes : ["M", "XL"]
    },
    {
        id : 6,
        name : "6 Lorem ipsum dolor sit amet",
        description : "6 Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus, dignissimos? Exercitationem iure architecto temporibus aliquam, numquam, culpa aperiam placeat voluptatem tempora nemo alias? Similique, rerum eius. Et provident voluptatem tenetur",
        images : [
            "https://images.unsplash.com/photo-1542219550-2da790bf52e9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzB8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1603252109360-909baaf261c7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzN8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1613461920867-9ea115fee900?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDB8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
            "https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDV8fGNsb3RoaW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60"
        ],
        rating : 4.5,
        price : 800000,
        sizes : ["S", "M", "L", "XL", "XXL"]
    }
]

