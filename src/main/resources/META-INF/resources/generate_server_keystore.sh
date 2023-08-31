keytool -genkey \
        -storepass server_password \
         -keyalg RSA \
         -keysize 2048 \
         -dname "CN=server" \
         -alias server \
         -ext "san:c=dns:localhost,IP:127.0.0.1" \
         -keystore server.keystore
         