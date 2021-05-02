package at.rt.simple.webshop.web.view.controller;

import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean haelt die Liste aller Primefaces-Themes fuer den Theme-Switcher im Header.
 */
@Named
@Scope("application")
public class ThemeSwitcherBean extends BaseBackingBean {

    private List<String> themes;

    @PostConstruct
    public void init() {
        initThemes();
    }

    public List<String> getThemes() {
        return themes;
    }

    /**
     * Formatiert den Theme-Name.<br>
     * Entfernt Bindestriche und schreibt den ersten Buchstaben gross.
     *
     * @param themeName Name der Theme
     * @return formatierter Name
     */
    public String formatThemeName(String themeName) {
        return themeName.substring(0, 1).toUpperCase() + themeName.substring(1).replace("-", "");
    }

    private void initThemes() {
        themes = new ArrayList<>();
        themes.add("afterdark");
        themes.add("afternoon");
        themes.add("afterwork");
        themes.add("aristo");
        themes.add("black-tie");
        themes.add("blitzer");
        themes.add("bluesky");
        themes.add("bootstrap");
        themes.add("casablanca");
        themes.add("cupertino");
        themes.add("cruze");
        themes.add("dark-hive");
        themes.add("delta");
        themes.add("dot-luv");
        themes.add("eggplant");
        themes.add("excite-bike");
        themes.add("flick");
        themes.add("glass-x");
        themes.add("home");
        themes.add("hot-sneaks");
        themes.add("humanity");
        themes.add("le-frog");
        themes.add("midnight");
        themes.add("mint-choc");
        themes.add("omega");
        themes.add("overcast");
        themes.add("pepper-grinder");
        themes.add("redmond");
        themes.add("rocket");
        themes.add("sam");
        themes.add("smoothness");
        themes.add("south-street");
        themes.add("start");
        themes.add("sunny");
        themes.add("swanky-purse");
        themes.add("trontastic");
        themes.add("ui-darkness");
        themes.add("ui-lightness");
        themes.add("vader");
    }
}