public interface MovieBarListener {
    public void pause();
    public void rewind();
    public void skip();
    public void play(String path);
    public void close();
}
