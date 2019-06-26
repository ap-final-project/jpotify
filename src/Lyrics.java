import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Lyrics {
    Elements lyricsTable;
    public Lyrics(String songName,String artistName) {
        songName = songName.replaceAll(" ", "+");
        artistName = artistName.replaceAll(" ", "+");
        String search=songName+"+"+artistName;
        String initialurl = "https://search.azlyrics.com/search.php?q=" + search;
        System.out.println(initialurl);
        try {
            Document site = Jsoup.connect(initialurl).get();
            Elements lyricsTable = site.select("div.panel");
            ArrayList<String> songNames = new ArrayList<>();
            ArrayList<String> urls = new ArrayList<>();
            for (Element elm : lyricsTable){
                if (elm.text().contains("Album")) {
                    continue;
                }
                //System.out.println(elm);
                Elements table = elm.select("table > tbody > tr");
                for (Element elms : table) {
                    if (elms.text().contains("More Song Results")) {
                        continue;
                    }
                    elms.select("small").html("");
                    songNames.add(elms.text());
                    urls.add(elms.select("a").attr("href"));

                }
            }
            for (int j = 0; j < urls.size(); j++) {
                System.out.println("-"+songNames.get(j));
            }
            Document lyricPage = Jsoup.connect(urls.get(1)).get();
            Elements lyricTags = lyricPage.select("div[class='col-xs-12 col-lg-8 text-center']>div");
            String lyrics = new String();
            for (Element elm : lyricTags) {
                if(elm.attr("class").equals("div-share noprint")||elm.attr("class").equals("collapse noprint")||elm.attr("class").equals("panel album-panel noprint")||elm.attr("class").equals("noprint")||elm.attr("class").equals("smt")||elm.attr("class").equals("hidden")||elm.attr("class").equals("smt noprint")||elm.attr("class").equals("div-share")||elm.attr("class").equals("lyricsh")||elm.attr("class").equals("ringtone")) {
                    continue;
                }
                lyrics = elm.text();
                break;
                //System.out.println(elm.text());
            }
            System.out.println(lyrics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lyrics lyrics=new Lyrics("hello","adele");
        for (Element elm : lyrics.lyricsTable){
            for (Element link:elm.getElementsByTag("a")) {

                System.out.println("hi "+link);
            }
        }
        }
    }

