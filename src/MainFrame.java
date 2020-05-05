import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    // Константы, задающие размер окна приложения, если оно
// не распахнуто на весь экран
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem pauMenuItem;
    private JMenuItem resMenuItem;
    private JMenuItem colourPause;
    private JMenuItem colourResume;


    // Поле, по которому прыгают мячи
    private Field field = new Field();

    // Конструктор главного окна приложения
    public MainFrame()
    {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        // Установить начальное состояние окна развѐрнутым на весь экран
        setExtendedState(MAXIMIZED_BOTH);

        // Создать меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() &&
                        !resumeMenuItem.isEnabled()) {
                    // Ни один из пунктов меню не являются
// доступными - сделать доступным "Паузу"
                    pauseMenuItem.setEnabled(true);

                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                field.paused();
                field.pauseColour();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
                pauMenuItem.setEnabled(false);
                resMenuItem.setEnabled(true);
                colourResume.setEnabled(true);
                colourPause.setEnabled(false);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение")
        {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                field.resumed();
                field.resumeColour();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
                pauMenuItem.setEnabled(true);
                resMenuItem.setEnabled(false);
                colourPause.setEnabled(true);
                colourResume.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);

        Action pauAction = new AbstractAction("Остановить по радиусу")
        {
            public void actionPerformed(ActionEvent event)
            {
                field.paused();
                pauMenuItem.setEnabled(false);
                resMenuItem.setEnabled(true);
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauMenuItem = controlMenu.add(pauAction);
        pauMenuItem.setEnabled(true);


        Action resAction = new AbstractAction("Радиусное возобновление")
        {
            public void actionPerformed(ActionEvent event)
            {
                field.resumed();
                pauMenuItem.setEnabled(true);
                resMenuItem.setEnabled(false);
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resMenuItem = controlMenu.add(resAction);
        resMenuItem.setEnabled(false);

        Action colourStopAction = new AbstractAction("Остановить по цвету")
        {
            public void actionPerformed(ActionEvent event)
            {
                field.pauseColour();
                colourPause.setEnabled(false);
                colourResume.setEnabled(true);
                resumeMenuItem.setEnabled(true);
            }
        };
        colourPause = controlMenu.add(colourStopAction);
        colourPause.setEnabled(true);

        Action colourResumeAction = new AbstractAction("Цветное возобновление")
        {
            public void actionPerformed(ActionEvent event)
            {
                field.resumeColour();
                colourPause.setEnabled(true);
                colourResume.setEnabled(false);
            }
        };
        colourResume = controlMenu.add(colourResumeAction);
        colourResume.setEnabled(false);

        // Добавить в центр граничной компоновки поле Field
        getContentPane().add(field, BorderLayout.CENTER);
    }

    // Главный метод приложения
    public static void main(String[] args) {
        // Создать и сделать видимым главное окно приложения
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}