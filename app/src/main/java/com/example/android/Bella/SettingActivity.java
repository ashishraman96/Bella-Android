package com.example.android.Bella;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;

public class SettingActivity extends MaterialAboutActivity {

    int colorIcon = R.color.colorIconDark;
    int red = R.color.red_500;
    int lightRed = R.color.red_400;
    int blue = R.color.light_blue_900;
    int yellow = R.color.yellow_600;
    int green = R.color.green_700;
    int purple = R.color.purple_500;
    int orange = R.color.orange_600;
    int grey = R.color.grey_500;
    int lightGreen = R.color.green_500;
    int brown = R.color.brown_500;

    @Override
    protected MaterialAboutList getMaterialAboutList(Context c) {

        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Bella")
                .icon(R.mipmap.bella_launcher)
                .build());

        try {

            appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                    new IconicsDrawable(c)
                            .icon(GoogleMaterial.Icon.gmd_info_outline)
                            .color(ContextCompat.getColor(c, red))
                            .sizeDp(18),
                    "Version",
                    false));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(c, brown))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases", "https://github.com/Bella-Assistant/Bella-Android/blob/master/CHANGELOG", true, false))
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title("Author");
//        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Null Pointers")
                .subText("Bangalore")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(ContextCompat.getColor(c, blue))
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Fork on GitHub")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(c, grey))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/ujwalp15/Bella")))
                .build());

        MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();

        convenienceCardBuilder.title("Convenience Builder");
        try {
            convenienceCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                    new IconicsDrawable(c)
                            .icon(CommunityMaterial.Icon.cmd_information_outline)
                            .color(ContextCompat.getColor(c, lightRed))
                            .sizeDp(18),
                    "Revision",
                    true));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        convenienceCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .color(ContextCompat.getColor(c, green))
                        .sizeDp(18),
                "Visit Website",
                true,
                Uri.parse("Coming soon!")));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .color(ContextCompat.getColor(c, yellow))
                        .sizeDp(18),
                "Rate this app",
                null
        ));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(c, grey))
                        .sizeDp(18),
                "Send an email",
                true,
                "ujwalp09@gmail.com",
                "Question concerning Bella"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_phone)
                        .color(ContextCompat.getColor(c, blue))
                        .sizeDp(18),
                "Call me",
                true,
                "9916637360"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createMapItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_map)
                        .color(ContextCompat.getColor(c, lightGreen))
                        .sizeDp(18),
                "Visit Bangalore",
                null,
                "bangalore"));

        MaterialAboutCard.Builder appCardBuilder1 = new MaterialAboutCard.Builder();
        appCardBuilder1.title("Other");

        appCardBuilder1.addItem(new MaterialAboutActionItem.Builder()
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_gavel)
                        .color(ContextCompat.getColor(c, brown))
                        .sizeDp(18))
                .text("The Drill")
                .subTextHtml("Logcat or GTFO")
                .setIconGravity(MaterialAboutActionItem.GRAVITY_TOP)
                .setOnClickListener(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Logcat or GTFO", "http://i.bittwiddlers.org/LIt.png", true, true))
                .build()
        );

        appCardBuilder1.addItem(new MaterialAboutActionItem.Builder()
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_language_html5)
                        .color(ContextCompat.getColor(c, orange))
                        .sizeDp(18))
                .text("Contributions")
                .subTextHtml("We believe in <b>Open-Source</b> ideology. <br />Therefore, the <i>source</i> is readily available to work with. This gives other developers a chance to contribute to our project<br/>Remember that we accept only pull-requests")
                .setIconGravity(MaterialAboutActionItem.GRAVITY_TOP)
                .build()
        );


        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), convenienceCardBuilder.build(), appCardBuilder1.build());
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }
}
