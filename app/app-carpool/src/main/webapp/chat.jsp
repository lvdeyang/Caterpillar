<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test</title>


</head>
<body>

	Local:
	<br>
	<video id="localVideo" autoplay style="width:200px;height:200px;"></video>
	<br> Remote:
	<br>
	<video id="remoteVideo" autoplay style="width:200px;height:200px;"></video>

	<script>
        // 仅仅用于控制哪一端的浏览器发起offer，#号后面有值的一方发起
        var isCaller = window.location.href.split('#')[1];

        // 与信令服务器的WebSocket连接
        var socket = new WebSocket("ws://127.0.0.1:8080/app-carpool/webSocket/chat/1");

        // stun和turn服务器
        var iceServer = {
            "iceServers": [{
                "url": "stun:stun.l.google.com:19302"
            }, {
                "url": "turn:numb.viagenie.ca",
                "username": "webrtc@live.com",
                "credential": "muazkh"
            }]
        };

        // 创建PeerConnection实例 (参数为null则没有iceserver，即使没有stunserver和turnserver，仍可在局域网下通讯)
        var pc = new webkitRTCPeerConnection(iceServer);

        // 发送ICE候选到其他客户端
        pc.onicecandidate = function(event){
            if (event.candidate !== null) {
                socket.send(JSON.stringify({
                    "event": "_ice_candidate",
                    "data": {
                        "candidate": event.candidate
                    }
                }));
            }
        };

        // 如果检测到媒体流连接到本地，将其绑定到一个video标签上输出
        pc.onaddstream = function(event){
            document.getElementById('remoteVideo').src = URL.createObjectURL(event.stream);
        };

        // 发送offer和answer的函数，发送本地session描述
        var sendOfferFn = function(desc){
            pc.setLocalDescription(desc);
            socket.send(JSON.stringify({ 
                "event": "_offer",
                "data": {
                    "sdp": desc
                }
            }));
        },
        sendAnswerFn = function(desc){
            pc.setLocalDescription(desc);
            socket.send(JSON.stringify({ 
                "event": "_answer",
                "data": {
                    "sdp": desc
                }
            }));
        };

        // 获取本地音频和视频流
        navigator.webkitGetUserMedia({
            "audio": false,
            "video": true
        }, function(stream){
            //绑定本地媒体流到video标签用于输出
            document.getElementById('localVideo').src = URL.createObjectURL(stream);
            //向PeerConnection中加入需要发送的流
            pc.addStream(stream);
            //如果是发起方则发送一个offer信令
            if(isCaller){
                pc.createOffer(sendOfferFn, function (error) {
                    console.log('Failure callback: ' + error);
                });
            }
        }, function(error){
            //处理媒体流创建失败错误
            console.log('getUserMedia error: ' + error);
        });

        //处理到来的信令
        socket.onmessage = function(event){
            var json = JSON.parse(event.data);
            console.log('onmessage: ', json);
            //如果是一个ICE的候选，则将其加入到PeerConnection中，否则设定对方的session描述为传递过来的描述
            if( json.event === "_ice_candidate" ){
                pc.addIceCandidate(new RTCIceCandidate(json.data.candidate));
            } else {
                pc.setRemoteDescription(new RTCSessionDescription(json.data.sdp));
                // 如果是一个offer，那么需要回复一个answer
                if(json.event === "_offer") {
                    pc.createAnswer(sendAnswerFn, function (error) {
                        console.log('Failure callback: ' + error);
                    });
                }
            }
        };
    </script>


</body>






</html>