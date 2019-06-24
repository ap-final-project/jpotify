//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.InputStream;
import java.util.ArrayList;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

import javax.swing.*;

public class MyPlayer {
    private int frame;
    private Bitstream bitstream;
    private Decoder decoder;
    public AudioDevice audio;
    private boolean closed;
    private boolean complete;
    private int lastPosition;
    private SampleBuffer var3;
    private short[] Equalizers=new short[32];
    public MyPlayer(InputStream var1) throws JavaLayerException {
        this(var1, null);
    }

    public MyPlayer(InputStream var1, AudioDevice var2) throws JavaLayerException {
        this.frame = 0;
        this.closed = false;
        this.complete = false;
        this.lastPosition = 0;
        this.bitstream = new Bitstream(var1);
        this.decoder = new Decoder();
        if (var2 != null) {
            this.audio = var2;
        } else {
            FactoryRegistry var3 = FactoryRegistry.systemRegistry();
            this.audio = var3.createAudioDevice();
        }

        this.audio.open(this.decoder);
    }

    public void play() throws JavaLayerException {
        this.play(2147483647);
    }

    public boolean play(int var1) throws JavaLayerException {
        boolean var2;
        for(var2 = true; var1-- > 0 && var2; var2 = this.decodeFrame()) {
        }

        if (!var2) {
            AudioDevice var3 = this.audio;
            if (var3 != null) {
                var3.flush();
                synchronized(this) {
                    this.complete = !this.closed;
                    this.close();
                }
            }
        }

        return var2;
    }

    public synchronized void close() {
        AudioDevice var1 = this.audio;
        if (var1 != null) {
            this.closed = true;
            this.audio = null;
            var1.close();
            this.lastPosition = var1.getPosition();

            try {
                this.bitstream.close();
            } catch (BitstreamException var3) {
            }
        }

    }

    public synchronized boolean isComplete() {
        return this.complete;
    }

    public int getPosition() {
        int var1 = this.lastPosition;
        AudioDevice var2 = this.audio;
        if (var2 != null) {
            var1 = var2.getPosition();
        }

        return var1;
    }

    protected boolean decodeFrame() throws JavaLayerException {
        try {
            AudioDevice var1 = this.audio;
            if (var1 == null) {
                return false;
            } else {
                Header var2 = this.bitstream.readFrame();
                if (var2 == null) {
                    return false;
                } else {
                    var3 = (SampleBuffer)this.decoder.decodeFrame(var2, this.bitstream);
                    synchronized(this) {
                        var1 = this.audio;
                        if (var1 != null) {
                            var1.write(var3.getBuffer(), 0, var3.getBufferLength());
                        }
                    }

                    this.bitstream.closeFrame();
                    return true;
                }
            }
        } catch (RuntimeException var7) {
            throw new JavaLayerException("Exception decoding audio frame", var7);
        }

    }
    public short[] getFrames(){
        return var3.getBuffer();
    }
    public boolean skipFrame() throws JavaLayerException
    {
        Header h = bitstream.readFrame();
        if (h == null) return false;
        bitstream.closeFrame();
        return true;

    }
    public long findNumbersOfFrame() throws JavaLayerException {

        boolean ret = true;
        long size = 0;

        while ( ret ) {

            ret = skipFrame();
            size++;
        }

        return size;
    }
}
