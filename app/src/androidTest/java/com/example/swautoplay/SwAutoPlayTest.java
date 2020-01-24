package com.example.swautoplay;

import android.content.Context;
import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.swautoplay.UIConstants.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SwAutoPlayTest {

    private static final int LAUNCH_TIMEOUT = 5000;
    private static final int TIMEOUT = 2000;
    private static final String SWAUTOPLAY_PACKAGE = "com.com2us.smon.normal.freefull.google.kr.android.common";

    private static final int DUNGEON_LAUNCH_TIME = 15;
    protected static final int RIVALS_COUNT = 9;

    private UiDevice device;
    private boolean play;
    private int dungeonDoneCount;
    private Configuration config;

    @Before
    public void run() {
        config = new Configuration().parse();
    }


    @Test
    public void Play() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();

        // Initialize UiDevice instance
        this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        this.play = false;
        this.dungeonDoneCount = 0;

        boolean isToA = "ToA".equals(config.startTestPosition);
        boolean isRivals = "Rivals".equals(config.startTestPosition);

        if ("Home".equals(config.startTestPosition)) {
            this.startAppUntilIsland(context);
        } else if ("Island".equals(config.startTestPosition) || isToA) {
            this.play = true;
        }

        if (!(isToA || isRivals)) {
            //Move to battle menu
            this.click(BATTLE_WIDTH_PERCENTAGE, BATTLE_HEIGHT_PERCENTAGE);
            this.wait(1);
        }

        switch (config.dungeonName) {
            case "Giant":
                this.runCairosDungeon(GIANT_WIDTH_PERCENTAGE, GIANT_HEIGHT_PERCENTAGE);
                break;

            case "Drake":
                this.runCairosDungeon(DRAKE_WIDTH_PERCENTAGE, DRAKE_HEIGHT_PERCENTAGE);
                break;

            case "Necropolis":
                this.runCairosDungeon(NECROPOLIS_WIDTH_PERCENTAGE, NECROPOLIS_HEIGHT_PERCENTAGE);
                break;

            case "ToA":
                this.runToA(isToA);
                break;

            case "Rivals":
                this.runRivals(isRivals);
                break;

            case "Beasts":
                this.handleBeasts();
                break;

            default:
                if ("Karzhan".equals(config.dungeonName) || "Ellunia".equals(config.dungeonName) || "Lumel".equals(config.dungeonName)) {
                    this.handleRift();
                } else {
                    this.handleScenario(config.dungeonName);
                }
                break;
        }
    }

    private void handleBeasts() throws UiObjectNotFoundException, InterruptedException {
        this.click(RIFT_WORLD_ENTRY_WIDTH_PERCENTAGE, RIFT_WORLD_ENTRY_HEIGHT_PERCENTAGE);
        this.click(CONFIRM_WORLD_RIFT_ENTRY_WIDTH_PERCENTAGE, CONFIRM_WORLD_RIFT_ENTRY_HEIGHT_PERCENTAGE);
        this.click(BEASTS_ENTRANCE_WIDTH_PERCENTAGE, BEASTS_ENTRANCE_HEIGHT_PERCENTAGE);
        double beast_height = RED_BEAST_HEIGHT_PERCENTAGE + BEAST_STEP * this.config.level;
        this.click(RED_BEAST_WIDTH_PERCENTAGE, beast_height);
        this.click(BEAST_BATTLE_WIDTH_PERCENTAGE, BEAST_BATTLE_HEIGHT_PERCENTAGE);
        this.launchDungeon(0);
    }

    private void handleRift() throws UiObjectNotFoundException, InterruptedException {
        UiScrollable scroller = new UiScrollable(new UiSelector().resourceId("com.com2us.smon.normal.freefull.google.kr.android.common:id/GLViewLayout"));
        scroller.setAsHorizontalList();
        scroller.scrollForward();
        scroller.scrollForward();
        this.wait(2);

        this.click(RIFT_STRUCTURE_WIDTH_PERCENTAGE, RIFT_STRUCTURE_HEIGHT_PERCENTAGE);
        this.click(CONFIRM_RIFT_ENTRY_WIDTH_PERCENTAGE, CONFIRM_RIFT_ENTRY_HEIGHT_PERCENTAGE);

        switch (config.dungeonName) {
            case "Karzhan":
                this.launchRiftDungeon(KARZHAN_WIDTH_PERCENTAGE, KARZHAN_HEIGHT_PERCENTAGE, KARZHAN_VESTIGE_WIDTH_PERCENTAGE, KARZHAN_VESTIGE_HEIGHT_PERCENTAGE,
                        KARZHAN_FOREST_WIDTH_PERCENTAGE, KARZHAN_FOREST_HEIGHT_PERCENTAGE);
                break;

            case "Ellunia":
                this.launchRiftDungeon(ELLUNIA_WIDTH_PERCENTAGE, ELLUNIA_HEIGHT_PERCENTAGE, ELLUNIA_VESTIGE_WIDTH_PERCENTAGE, ELLUNIA_VESTIGE_HEIGHT_PERCENTAGE,
                        ELLUNIA_SANCTUARY_WIDTH_PERCENTAGE, ELLUNIA_SANCTUARY_HEIGHT_PERCENTAGE);
                break;

            case "Lumel":
                this.launchRiftDungeon(LUMEL_WIDTH_PERCENTAGE, LUMEL_HEIGHT_PERCENTAGE, ELLUNIA_VESTIGE_WIDTH_PERCENTAGE, ELLUNIA_VESTIGE_HEIGHT_PERCENTAGE,
                        ELLUNIA_SANCTUARY_WIDTH_PERCENTAGE, ELLUNIA_SANCTUARY_HEIGHT_PERCENTAGE);
                break;

            default:
                break;
        }
    }

    private void launchRiftDungeon(double zoneWidth, double zoneHeight, double vestigeWidth, double vestigeHeight, double dungeonWidth, double dungeonHeight) throws InterruptedException {
        this.click(zoneWidth, zoneHeight);
        if ("Vestige".equals(config.difficulty)) {//Vestige
            this.click(vestigeWidth, vestigeHeight);
            if (config.startStage == 0) {
                this.click(LEFT_RIFT_MONSTER_WIDTH_PERCENTAGE, LEFT_RIFT_MONSTER_HEIGHT_PERCENTAGE);
            } else {
                this.click(RIGHT_RIFT_MONSTER_WIDTH_PERCENTAGE, RIGHT_RIFT_MONSTER_HEIGHT_PERCENTAGE);
            }
        } else {//Rune dungeon
            this.click(dungeonWidth, dungeonHeight);
        }
        this.click(SELECTED_RIFT_LEVEL_WIDTH_PERCENTAGE, SELECTED_RIFT_LEVEL_HEIGHT_PERCENTAGE);
        int riftLevel = config.level;
        this.click(FIRST_RIFT_LEVEL_WIDTH_PERCENTAGE + RIFT_LEVEL_SPACE * (riftLevel-1), SELECTED_RIFT_LEVEL_HEIGHT_PERCENTAGE);
        this.click(LAUNCH_RIFT_DUNGEON_WIDTH_PERCENTAGE, SELECTED_RIFT_LEVEL_HEIGHT_PERCENTAGE);

        this.launchDungeon(0);
    }

    private void handleScenario(String dungeonName) throws UiObjectNotFoundException, InterruptedException {
        int dungeonId = UIConstants.getScenarioId(dungeonName);
        if (dungeonId != -1) {
            this.runScenario(dungeonId);
        }
    }

    private void startAppUntilIsland(Context context) throws InterruptedException, UiObjectNotFoundException {
        // Start from the home screen
        device.pressHome();
//        throw new Exception(this.device.getDisplayHeight() + "\n" + this.device.getDisplayWidth() + "\n" + CASH_AD_CLOSE_WIDTH_PERCENTAGE*this.device.getDisplayHeight() + "\n" + this.device.getDisplayWidth()*CASH_AD_CLOSE_HEIGHT_PERCENTAGE);
        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(SWAUTOPLAY_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(SWAUTOPLAY_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);

        this.wait(60);

        boolean advertExists = true;
        while (advertExists) {
            advertExists = UIUtils.ClickById("com.com2us.smon.normal.freefull.google.kr.android.common:id/tv_promotion_view_custom_lower_left_once_at_a_day", device, TIMEOUT);
        }
        this.wait(1);

        //Touch somewhere to play
        this.click(CLICK_SOMEWHERE_WIDTH_PERCENTAGE, CLICK_SOMEWHERE_HEIGHT_PERCENTAGE);
        this.wait(1);

        //Remove cash ad
        this.skipCashAdvert();
        this.wait(1);

        //Remove chest panel
        this.click(CHEST_WIDTH_PERCENTAGE, CHEST_HEIGHT_PERCENTAGE);
        this.wait(1);

        //Remove cash ad
        this.skipCashAdvert();
        this.wait(1);
    }

    private void runRivals(boolean isRivals) throws InterruptedException, UiObjectNotFoundException {
        if (!isRivals) {
            this.click(ARENA_BAT_WIDTH_PERCENTAGE, ARENA_BAT_HEIGHT_PERCENTAGE);

            this.click(ARENA_WIDTH_PERCENTAGE, ARENA_HEIGHT_PERCENTAGE);

            this.click(RIVAL_WIDTH_PERCENTAGE, RIVAL_HEIGHT_PERCENTAGE);
        }

        for (int i = 0; i < RIVALS_COUNT; i++) {
            if (i == 5) {
                UiScrollable scroller = new UiScrollable(new UiSelector().resourceId("android:id/content"));
                scroller.setAsVerticalList();
                scroller.scrollToEnd(20);
                this.wait(10);
            }
            if (this.config.availableRivals[i]) {
                this.click(RIVALS_WIDTH_PERCENTAGE[i], RIVALS_HEIGHT_PERCENTAGE);
                if (i == 0) {
                    this.click(SAVED_TEAMS_WIDTH_PERCENTAGE, SAVED_TEAMS_HEIGHT_PERCENTAGE);
                    this.click(SAVED_TEAM_1_WIDTH_PERCENTAGE, SAVED_TEAM_1_HEIGHT_PERCENTAGE);
                }
                this.launchDungeon(2);
            }
        }
    }

    private void runScenario(int dungeonId) throws UiObjectNotFoundException, InterruptedException {
        UiScrollable scroller = new UiScrollable(new UiSelector().resourceId("com.com2us.smon.normal.freefull.google.kr.android.common:id/GLViewLayout"));
        scroller.setAsHorizontalList();
        if (dungeonId == 6) {
            scroller.scrollForward();
        } else if (dungeonId > 6) {
            scroller.scrollForward();
            scroller.scrollForward();
        }
        this.wait(2);
        this.click(SCENARIOS_WIDTH_PERCENTAGE[dungeonId], SCENARIOS_HEIGHT_PERCENTAGE[dungeonId]);

        this.click(SCENARIO_DIFFICULTY_WIDTH_PERCENTAGE, SCENARIO_DIFFICULTY_HEIGHT_PERCENTAGE);

        //Change difficulty
        switch (config.difficulty) {
            case "Hard":
                this.click(SCENARIO_HARD_WIDTH_PERCENTAGE, SCENARIO_HARD_HEIGHT_PERCENTAGE);
                break;

            case "Normal":
                this.click(SCENARIO_NORMAL_WIDTH_PERCENTAGE, SCENARIO_NORMAL_HEIGHT_PERCENTAGE);
                break;

            case "Hell":
                this.click(SCENARIO_HELL_WIDTH_PERCENTAGE, SCENARIO_HELL_HEIGHT_PERCENTAGE);
                break;
            default:
                break;
        }

        /*Can't scroll on scenario stages view -> can only run stages n°1 to 4*/
//        if (config.startStage > 4) {
//            UiScrollable scroller2 = new UiScrollable(new UiSelector().resourceId("com.com2us.smon.normal.freefull.google.kr.android.common:id/TextInputLayout"));
//            scroller2.setAsVerticalList();
//            scroller2.scrollToEnd(20);
//            this.wait(2);
//            scroller2.scrollToBeginning(20);
//            this.wait(2);
//            scroller2.scrollForward(20);
//            this.wait(2);
//            scroller2.scrollBackward(20);
//            this.wait(2);
//        }

        this.click(SCENARIO_STAGES_WIDTH_PERCENTAGE[config.startStage - 1], SCENARIO_STAGES_HEIGHT_PERCENTAGE);

        this.launchDungeon(1);
    }


    private void runToA(boolean isStartToA) throws InterruptedException {
        if (!isStartToA) {
            //Click ToA
            this.click(TOA_WIDTH_PERCENTAGE, TOA_HEIGHT_PERCENTAGE);

            //Click difficulty
            this.click(TOA_DIFFICULTY_WIDTH_PERCENTAGE, TOA_DIFFICULTY_HEIGHT_PERCENTAGE);

            //Change difficulty
            switch (config.difficulty) {
                case "Hard":
                    this.click(TOA_HARD_WIDTH_PERCENTAGE, TOA_HARD_HEIGHT_PERCENTAGE);
                    break;

                case "Normal":
                    this.click(TOA_NORMAL_WIDTH_PERCENTAGE, TOA_NORMAL_HEIGHT_PERCENTAGE);
                    break;

                default:
                    break;
            }
        }


        //Click start stage
        int stage = (config.startStage % 10) - 1;
        if (stage == -1) {
            stage = 9;
        }
        this.click(TOA_STAGES_WIDTH_PERCENTAGE[stage], TOA_STAGES_HEIGHT_PERCENTAGE[stage]);
        this.launchDungeon(0);
    }

    /* Runs a Cairos dungeon if on Cairos dungeon screen
     * */
    private void runCairosDungeon(double dungeonWidth, double dungeonHeight) throws InterruptedException, UiObjectNotFoundException {
        //Click Cairos dungeon
        this.click(CAIROS_WIDTH_PERCENTAGE, CAIROS_HEIGHT_PERCENTAGE);
        this.wait(1);

        //Click chosen dungeon
        if (this.config.isHoH) {
            dungeonWidth += 0.15;
        }
        this.click(dungeonWidth, dungeonHeight);

        UiScrollable scroller = new UiScrollable(new UiSelector().resourceId("com.com2us.smon.normal.freefull.google.kr.android.common:id/GLViewLayout"));
        scroller.scrollToEnd(20);
        this.wait(1);
        this.click(B10_WIDTH_PERCENTAGE, B10_HEIGHT_PERCENTAGE);

        this.launchDungeon(0);
    }

    private void launchOneArenaBattle() throws InterruptedException {
        //Click go button to launch dungeon
        this.click(DUNGEON_GO_WIDTH_PERCENTAGE, DUNGEON_GO_HEIGHT_PERCENTAGE);

        this.click(AUTO_PLAY_WIDTH_PERCENTAGE, AUTO_PLAY_HEIGHT_PERCENTAGE);//dialog panel
        this.click(AUTO_PLAY_WIDTH_PERCENTAGE, AUTO_PLAY_HEIGHT_PERCENTAGE);//auto play

        //Wait for the run to end
        this.wait(config.averageDungeonTime + 20);// + 20 = error marge

        this.click(CLICK_SOMEWHERE_WIDTH_PERCENTAGE, CLICK_SOMEWHERE_HEIGHT_PERCENTAGE);
        this.click(AUTO_PLAY_WIDTH_PERCENTAGE, AUTO_PLAY_HEIGHT_PERCENTAGE);//dialog panel
    }

    /* Handle dungeons runs replay
    dungeonType : 0 = Cairos dungeon
                  1 = Scenario
                  2 =
    * */
    private void launchDungeon(int dungeonType) throws InterruptedException {
        //Click go button to launch dungeon
        this.click(DUNGEON_GO_WIDTH_PERCENTAGE, DUNGEON_GO_HEIGHT_PERCENTAGE);

        do {
            //Wait for launch time
            this.wait(DUNGEON_LAUNCH_TIME);

            if (!this.play) {//First dungeon, activate auto play
                this.click(AUTO_PLAY_WIDTH_PERCENTAGE, AUTO_PLAY_HEIGHT_PERCENTAGE);
                this.play = true;
            }

            //Wait for the run to end
            this.wait(config.averageDungeonTime + 20);// + 20 = error marge

            this.handleReward(dungeonType);

            //Click go button to launch dungeon
            this.click(DUNGEON_GO_WIDTH_PERCENTAGE, DUNGEON_GO_HEIGHT_PERCENTAGE);

            this.dungeonDoneCount++;

            if (this.dungeonDoneCount < config.runCount) {
                this.handleRefill();
            }

        } while (this.dungeonDoneCount < config.runCount);
    }

    private void handleReward(int dungeonType) throws InterruptedException {
        //Click chest
        this.click(CLICK_SOMEWHERE_WIDTH_PERCENTAGE, CLICK_SOMEWHERE_HEIGHT_PERCENTAGE);

        this.handleLose();

        if (dungeonType == 1) {//sell only scenario rewards
            this.click(REWARD_SELL_WIDTH_PERCENTAGE, REWARD_SELL_HEIGHT_PERCENTAGE);
            this.click(SCENARIO_CONFIRM_SELLING_WIDTH_PERCENTAGE, SCENARIO_CONFIRM_SELLING_HEIGHT_PERCENTAGE);
        } else {
            this.click(REWARD_GET_WIDTH_PERCENTAGE, REWARD_GET_HEIGHT_PERCENTAGE);
        }

        this.click(REWARD_OK_WIDTH_PERCENTAGE, REWARD_OK_HEIGHT_PERCENTAGE);

        if (this.dungeonDoneCount < config.runCount - 1) {
            this.click(REPLAY_WIDTH_PERCENTAGE, REPLAY_HEIGHT_PERCENTAGE);
        }
    }

    private void handleLose() throws InterruptedException {
        this.click(NO_REANIMATE_WIDTH_PERCENTAGE, NO_REANIMATE_HEIGHT_PERCENTAGE);

        this.click(CLICK_SOMEWHERE_WIDTH_PERCENTAGE, CLICK_SOMEWHERE_HEIGHT_PERCENTAGE);

        this.click(REPLAY_WIDTH_PERCENTAGE, REPLAY_HEIGHT_PERCENTAGE);

        this.click(DUNGEON_GO_WIDTH_PERCENTAGE, DUNGEON_GO_HEIGHT_PERCENTAGE);
    }

    private void handleRefill() throws InterruptedException {
        switch (this.config.refillState) {
            case "Chest":
                this.click(REFILL_CHEST_WIDTH_PERCENTAGE, REFILL_CHEST_HEIGHT_PERCENTAGE);

                this.click(REFILL_CHEST_RECEIVE_WIDTH_PERCENTAGE, REFILL_CHEST_RECEIVE_HEIGHT_PERCENTAGE);

                this.click(CHEST_REFILL_CROSS_WIDTH_PERCENTAGE, CHEST_REFILL_CROSS_HEIGHT_PERCENTAGE);
                break;

            case "SocialPoint":
                handleShopRefill(SP_REFILL_WIDTH_PERCENTAGE, SP_REFILL_HEIGHT_PERCENTAGE);
                break;

            case "Crystals":
                handleShopRefill(CRYSTALS_REFILL_WIDTH_PERCENTAGE, CRYSTALS_REFILL_HEIGHT_PERCENTAGE);
                break;

            default:
                return;
        }
        this.click(REPLAY_WIDTH_PERCENTAGE, REPLAY_HEIGHT_PERCENTAGE);
    }

    private void handleShopRefill(double refillTypeWidth, double refillTypeHeight) throws InterruptedException {
        this.click(REFILL_SHOP_WIDTH_PERCENTAGE, REFILL_SHOP_HEIGHT_PERCENTAGE);

        this.click(refillTypeWidth, refillTypeHeight);

        this.click(SHOP_CONFIRM_WIDTH_PERCENTAGE, SHOP_CONFIRM_HEIGHT_PERCENTAGE);
        this.wait(1);

        this.click(SHOP_PURCHASE_OK_WIDTH_PERCENTAGE, SHOP_PURCHASE_OK_HEIGHT_PERCENTAGE);

        this.click(SHOP_REFILL_CROSS_WIDTH_PERCENTAGE, SHOP_REFILL_CROSS_HEIGHT_PERCENTAGE);

    }

    private void skipCashAdvert() throws InterruptedException {
        for (double i = -0.01; i < 0.01; i += 0.005) {
            this.click(CASH_AD_CROSS_WIDTH_PERCENTAGE + i, CASH_AD_CROSS_HEIGHT_PERCENTAGE + i);
        }
//        this.click(CASH_AD_CLOSE_WIDTH_PERCENTAGE, CASH_AD_CLOSE_HEIGHT_PERCENTAGE);
    }

    private void click(double widthPercentage, double heightPercentage) throws InterruptedException {
        this.device.click((int) (this.device.getDisplayWidth() * heightPercentage), (int) (this.device.getDisplayHeight() * widthPercentage));
        this.wait(1);
    }

    private void wait(int second) throws InterruptedException {
        synchronized (this.device) {
            this.device.wait(second * 1000);
        }
    }
}