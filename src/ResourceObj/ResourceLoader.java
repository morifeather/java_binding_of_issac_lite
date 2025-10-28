package ResourceObj;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ResourceLoader {
    private static final ResourceLoader instance=new ResourceLoader();
    public static ResourceLoader getInstance(){
        return instance;
    }


    public ArrayList<BufferedImage> BulletImage = new ArrayList<>();
    public ArrayList<BufferedImage> PoopImage = new ArrayList<>();
    public ArrayList<BufferedImage> ShootStoneImage = new ArrayList<>();
    public ArrayList<BufferedImage> GoldenPoopImage = new ArrayList<>();
    public ArrayList<BufferedImage> StoneImage = new ArrayList<>();
    public ArrayList<BufferedImage> BackGroundImage = new ArrayList<>();
    public ArrayList<BufferedImage> EnemyBulletImage = new ArrayList<>();
    public ArrayList<BufferedImage> DoorImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> TurkeyRightImage = new ArrayList<>();
    public ArrayList<BufferedImage> TurkeyLeftImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> FlyImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> TentacleImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> RedFlyRightImage = new ArrayList<>();
    public ArrayList<BufferedImage> RedFlyLeftImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> HeartImage= new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> PrideHeadImage= new ArrayList<>();
    public ArrayList<BufferedImage> PrideBodyImage= new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> IssacBulletDeleteImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public ArrayList<BufferedImage> IssacDeathImage = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------------------------*/
    public void ResourceLoad(){
        try {
            /*try {
                URL url0 = getClass().getClassLoader().getResource("images/BackGround/BackGround0.png");
                URL url1 = getClass().getClassLoader().getResource("images/BackGround/BackGround1.png");

                if (url0 != null && url1 != null) {
                    BackGroundImage.add(ImageIO.read(url0));
                    BackGroundImage.add(ImageIO.read(url1));
                    System.out.println("Background image resource loaded.");
                } else {
                    System.out.println("Background image resource not found.");
                }
            } catch (IOException e) {
                System.out.println("ResourceLoader Load Image Failed: " + e.getMessage());
                e.printStackTrace();
            }*/
            /*-------------------------------------------------------------------------------------------------------------*/
            BackGroundImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BackGround/BackGround0.png")));
            BackGroundImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BackGround/BackGround1.png")));
            BackGroundImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BackGround/BackGround1.png")));
            BackGroundImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BackGround/BackGround1.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            IssacDeathImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/IssacDeath/IssacDeath1.png")));
            IssacDeathImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/IssacDeath/IssacDeath2.png")));
            IssacDeathImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/IssacDeath/IssacDeath3.png")));
            IssacDeathImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Issac/IssacDeath/IssacDeath4.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete1.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete2.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete3.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete4.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete5.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete6.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete7.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete8.png")));
            IssacBulletDeleteImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/BulletDelete/BulletDelete9.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            PrideBodyImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Pride/PrideBody/PrideBody1.png")));
            PrideBodyImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Pride/PrideBody/PrideBody2.png")));
            PrideHeadImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Pride/PrideHead/PrideHead1.png")));
            PrideHeadImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Pride/PrideHead/PrideHead2.png")));
            PrideHeadImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Pride/PrideHead/PrideHead3.png")));
            PrideHeadImage .add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Pride/PrideHead/PrideHead4.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            HeartImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Heart/Heart1.png")));
            HeartImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Heart/Heart2.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            RedFlyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/RedFly/RedFly1.png")));
            RedFlyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/RedFly/RedFly2.png")));
            RedFlyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/RedFly/RedFly3.png")));
            RedFlyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/RedFly/RedFly4.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            TentacleImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Tentacle/Tentacle1.png")));
            TentacleImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Tentacle/Tentacle2.png")));
            TentacleImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Tentacle/Tentacle3.png")));
            TentacleImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Tentacle/Tentacle4.png")));
            TentacleImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Tentacle/Tentacle5.png")));
            TentacleImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Tentacle/Tentacle6.png")));
            /*-------------------------------------------------------------------------------------------------------------*/
            FlyImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Fly/Fly1.png")));
            FlyImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Fly/Fly2.png")));
            FlyImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Fly/Fly3.png")));
            FlyImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Fly/Fly4.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft1.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft2.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft3.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft4.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft5.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft6.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft7.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft8.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft9.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft10.png")));
            TurkeyLeftImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyLeft11.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight1.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight2.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight3.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight4.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight5.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight6.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight7.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight8.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight9.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight10.png")));
            TurkeyRightImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Turkey/TurkeyRight11.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorCloseUp.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorCloseDown.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorCloseLeft.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorCloseRight.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorOpenUp.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorOpenDown.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorOpenLeft.png")));
            DoorImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Door/DoorOpenRight.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            BulletImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Bullet/IssacBullet7.png")));
            BulletImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Bullet/EnemyBullet.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            EnemyBulletImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Bullet/EnemyBullet.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            PoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Poop/Poop5.png")));
            PoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Poop/Poop4.png")));
            PoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Poop/Poop3.png")));
            PoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Poop/Poop2.png")));
            PoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Poop/Poop1.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone1.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone2.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone3.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone4.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone5.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone6.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone7.png")));
            ShootStoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/ShootStone/ShootStone8.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            GoldenPoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/GoldenPoop/GoldenPoop1.png")));
            GoldenPoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/GoldenPoop/GoldenPoop2.png")));
            GoldenPoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/GoldenPoop/GoldenPoop3.png")));
            GoldenPoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/GoldenPoop/GoldenPoop4.png")));
            GoldenPoopImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/GoldenPoop/GoldenPoop5.png")));
            //-------------------------------------------------------------------------------------------------------------------//
            StoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Stone/Stone1.png")));
            StoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Stone/Stone2.png")));
            StoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Stone/Stone3.png")));
            StoneImage.add(ImageIO.read(Issac.class.getClassLoader().getResource("images/Obstacle/Stone/Unbreakable.png")));
        }
        catch (IOException e){
            System.out.println("ResourceLoader Load Image Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
