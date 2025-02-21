package calculator;

public class DiceRollWorker {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("사용법: java DiceRollWorker <rolls>");
            System.exit(1);
        }
        int numRolls = Integer.parseInt(args[0]);
        Dice dice = Dice.createDice(new RandomNumber());
        for (int i = 0; i < numRolls; i++) {
            dice.roll();
        }
        long pid = ProcessHandle.current().pid();
        System.out.println("프로세스 ID: " + pid + " - 작업 완료: " + numRolls + " 회 주사위 굴림");
    }

}
