/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.projecto8.whiteboard.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import pt.uc.dei.aor.projecto8.whiteboard.messages.SenderBeanBytes;
import pt.uc.dei.aor.projecto8.whiteboard.pojo.CountingPojo;

/**
 *
 * @author User
 */
@Stateless
@ServerEndpoint(value = "/whiteboardendpoint", encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class MyWhiteboard {

    @Inject
    private SenderBeanBytes senderBean;
    private static int numEdit = 0;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private static ByteBuffer bytebuffer = ByteBuffer.allocate(100000);
    private String userName = "";

    private CountingPojo countingPojo = new CountingPojo();

    @OnMessage
    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException {
        System.out.println("broadcastFigure: " + figure);

        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(figure);
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException {
        peers.add(peer);
        if (peer.getUserPrincipal() != null) {
            numEdit++;
        }
        sendNumber(numEdit);
        peer.getBasicRemote().sendBinary(bytebuffer);

    }

    public void sendNumber(int number) throws IOException {
        for (Session p : peers) {
            p.getBasicRemote().sendText("{\"npeers\" : " + number + "}");
        }
    }

    @OnClose
    public void onClose(Session peer) throws IOException {
        userName = peer.getId();
        peers.remove(peer);
        if (numEdit > 0) {
            numEdit--;
        }
        sendNumber(numEdit);

    }

    @OnMessage
    public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendBinary(data);
            }
        }

        senderBean.sendMessage(data);
        bytebuffer = data;
    }

    public void onJMSMessage(ByteBuffer msg) {
        try {
            bytebuffer = msg;
            for (Session s : peers) {

                s.getBasicRemote().sendBinary(bytebuffer);

            }
        } catch (IOException ex) {
            Logger.getLogger(MyWhiteboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ByteBuffer getBytebuffer() {
        return bytebuffer;
    }

    public void setBytebuffer(ByteBuffer bytebuffer) {
        MyWhiteboard.bytebuffer = bytebuffer;
    }

}
