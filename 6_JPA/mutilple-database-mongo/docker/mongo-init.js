db.createUser(
    {
        user: "admin",
        pwd: "111",
        roles: [{role: "dbOwner", db: "db-user"}]
    }
)

db.createUser(
    {
        user: "admin",
        pwd: "222",
        roles: [{role: "dbOwner", db: "db-post"}]
    }
)

db.createUser(
    {
        user: "admin",
        pwd: "333",
        roles: [{role: "dbOwner", db: "db-product"}]
    }
)