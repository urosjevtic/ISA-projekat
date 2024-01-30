
# Loadbalancer instrukcije

1. Skinuti Nginx

2. U nginx/conf premjestiti fajl **nginx.conf** koji koristimo za primjer

3. Pokrenuti nginx:
    - Za Linux:
        ```
        $ sudo systemctl start nginx
        ```
    - Za Windows:
        ```
        nginx
        ```

4. Potrebno je za demonstraciju pokrenuti dvije instance backend servera:
    - U intellij-u je potrebno otici na edit-configurations
    - Potom treba dodati novu konfiguraciju 
    - U **VM options** potrebno je dodati sledecu liniju
     ```
     -Dserver.port=8081
     ```
5. Testirati uz pomoc postman-a ili drugih alata
6. Nginx zaustavljamo:
    - Za Linux:
        ```
        $ sudo systemctl stop nginx
        ```
    - Za Windows:
        ```
        nginx -s stop
        ```



