const logout = async () => {
    try {
        await axios.post("/api/v1/logout");

        window.location.href = "/";
    } catch (error) {
        console.log(error);
    }
}