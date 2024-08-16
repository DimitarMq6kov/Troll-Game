package utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int WALKING= 1;
        public static final int RUNNING = 2;
        public static final int JUMPING = 3;
        public static final int HURT = 4;
        public static final int DEAD = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_2 = 7;
        public static final int ATTACK_3 = 8;
        public static final int ATTACK_4 = 9;
        public static final int ATTACK_PARTICLE_1 = 10;
        public static final int ATTACK_PARTICLE_2 = 11;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDLE:
                case RUNNING:
                case JUMPING:
                    return 8;
                case WALKING:
                case ATTACK_3:
                    return 7;
                case HURT:
                case DEAD:
                    return 4;
                case ATTACK_1:
                case ATTACK_PARTICLE_2:
                    return 6;
                case ATTACK_4:
                case ATTACK_PARTICLE_1:
                    return 9;
                case ATTACK_2:
                    return 16;
                default:
                    return 0;
            }
        }
    }
}
