import javazoom.jl.decoder.JavaLayerException;

public interface ProgressBarUpdateListener {
    void update(int percent) throws JavaLayerException;
}
