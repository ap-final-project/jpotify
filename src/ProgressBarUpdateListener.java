import javazoom.jl.decoder.JavaLayerException;

public interface ProgressBarUpdateListener {
    public void update(int percent) throws JavaLayerException;
}
