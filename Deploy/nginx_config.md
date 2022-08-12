```yaml
server {
    listen 80 default_server;
    listen [::]:80 default_server;

    # Domain name của web app, có thể một hoặc nhiều domain cùng trỏ đến
    server_name course.buihien.tech;

    # Forward toàn bộ request sang web app
    location / {
        # Thay đổi port nếu node-web-app chạy trên port khác 3000
        proxy_pass http://localhost:8089;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
```
