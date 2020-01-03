sudo mkdir /opt/ManageMyLife
sudo cp -r * /opt/ManageMyLife
sudo chmod 755 /opt/ManageMyLife/Servidor/server.sh
sudo chmod -R 777 /opt/ManageMyLife/Escritorio/informes_generados
sudo chmod -R 777 /opt/ManageMyLife/Servidor/reports
sudo chmod -R 777 /opt/ManageMyLife/Servidor/media
sudo chmod +x /opt/ManageMyLife/Escritorio/ejecutar.sh
mysql -u root -p < bd.sql
sudo mv /opt/ManageMyLife/Escritorio/escritorio.desktop /usr/share/applications/escritorio.desktop
cp /usr/share/applications/escritorio.desktop ~/Escritorio/escritorio.desktop
sudo chown "$USER" ~/Escritorio/escritorio.desktop
sudo chmod +x ~/Escritorio/escritorio.desktop
sudo mv /opt/ManageMyLife/Servidor/server.service /etc/systemd/system/server.service
systemctl daemon-reload
systemctl enable server.service
systemctl start server.service
