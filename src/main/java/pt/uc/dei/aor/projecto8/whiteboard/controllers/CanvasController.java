/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.projecto8.whiteboard.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import pt.uc.dei.aor.projecto8.whiteboard.beans.LoggedUser;
import pt.uc.dei.aor.projecto8.whiteboard.facades.WhiteboardFacade;
import pt.uc.dei.aor.projecto8.whiteboard.websocket.MyWhiteboard;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Vitor
 */
@Named
@RequestScoped
public class CanvasController implements Serializable {
    @Inject
    private MyWhiteboard mywhiteboard;
    
    @Inject
    private LoggedUser loggedUser;
    
    @Inject 
    private WhiteboardFacade whiteboardfacade;
    
    private String dataURL;

    public String getDataURL() {
        return dataURL;
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    public void submit() throws IOException {
        System.out.println(dataURL);
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) external.getContext();
        String filename = servletContext.getRealPath("cloud.png");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = decoder.decodeBuffer(dataURL.split("^data:image/(png|jpg);base64,")[1]);
        BufferedImage imag = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        ImageIO.write(imag, "png", new File(filename));
        System.out.println(imag);
    }

    public void test(){
        byte[] testing = mywhiteboard.getBytebuffer().array();
        
//        for (int i = 0; i < testing.length; i++) {
//            System.out.println(i+">"+testing.toString());
//        }
        
        whiteboardfacade.insertImage("teste", testing, loggedUser.getLoggedUser());
        
    }
    
}
