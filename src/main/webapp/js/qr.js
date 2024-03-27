let codigo = document.getElementById("codigo").textContent;
let qrImage = document.getElementById("qrImage");

qrImage.src = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+codigo

