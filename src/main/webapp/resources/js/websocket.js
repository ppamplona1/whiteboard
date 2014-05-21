/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri;
if (document.location.protocol === "https:") {
    wsUri = "wss://" + document.location.host + "/Whiteboard/whiteboardendpoint";
  
}
else {
    wsUri = "ws://" + document.location.host + "/Whiteboard/whiteboardendpoint";
  
}
alert(wsUri);

var websocket = new WebSocket(wsUri);

websocket.binaryType = "arraybuffer";

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

websocket.onmessage = function(evt) { onMessage(evt) };

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                
function onMessage(evt) {
    console.log("received: " + evt.data);
    if (typeof evt.data == "string") {
        drawImageText(evt.data);
    } else {
        drawImageBinary(evt.data);
    }
}

function sendBinary(bytes) {
    console.log("sending binary: " + Object.prototype.toString.call(bytes));
    websocket.send(bytes);
}