package Seccion_4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.poifs.crypt.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ProductApp extends JFrame {
    // Campo para el nombre del producto
    private JTextField nameField;
    // Campo para el precio
    private JTextField priceField;
    // Campo para la cantidad en stock
    private JTextField stockField;
    // Área de texto donde se listarán los productos registrados
    private JTextArea productDisplay;
    // Lista en memoria donde se guardan los objetos Product
    private ArrayList<Product> productList;

    // Constructor: configura la interfaz grafica y los componentes.
    public ProductApp() {
        super("Gestión de Productos");
        productList = new ArrayList<>(); // Inicializa la lista de productos

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Layout vertical
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        // Logo Bancolombia
        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Bancolombia.png"));
        if (logoIcon.getIconWidth() != -1) {

            Image scaledImage = logoIcon.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        }
        mainPanel.add(logoLabel);

        // Formulario
        // 4 filas, 2 columnas y 10px de espacio entre celdas
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setMaximumSize(new Dimension(400, 150)); // Evita que el panel se estire demasiado

        formPanel.add(new JLabel("Nombre del producto:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Precio:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Cantidad en stock:"));
        stockField = new JTextField();
        formPanel.add(stockField);

        // Botones
        // Crear los botones y asignarles acción
        JButton saveButton = new JButton("Guardar Producto");
        JButton exportButton = new JButton("Exportar a Excel");
        saveButton.addActionListener(e -> saveProduct());
        exportButton.addActionListener(e -> exportToExcel());

        // Definir un tamaño para ambos botones
        Dimension buttonSize = new Dimension(200, 30);
        saveButton.setMaximumSize(buttonSize);
        exportButton.setMaximumSize(buttonSize);

        // Colocar los botones en un panel vertical
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio de 10px
        buttonPanel.add(exportButton);

        // Agregar formulario y botones al panel principal, con espacios
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(buttonPanel);

        // Área de visualización
        JLabel displayLabel = new JLabel("Productos registrados:");
        displayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(displayLabel);

        productDisplay = new JTextArea(10, 30);
        productDisplay.setEditable(false); // Solo lectura
        JScrollPane scrollPane = new JScrollPane(productDisplay);
        mainPanel.add(scrollPane);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termina la aplicación al cerrar
        this.pack(); // Ajusta tamaño automáticamente
        this.setLocationRelativeTo(null); // Centra en pantalla
        this.setVisible(true); // Hace la ventana visible
    }

    // Lee los campos de texto, valida los datos y crea un nuevo producto.
    private void saveProduct() {
        String name = nameField.getText();
        double price;
        int stock;
        try {
            // Intentar convertir los valores ingresados a números
            price = Double.parseDouble(priceField.getText());
            stock = Integer.parseInt(stockField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Precio o stock inválidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Crear el objeto producto y añadirlo a la lista en memoria
        Product newProduct = new Product(name, price, stock);
        productList.add(newProduct);

        // Notificar éxito al usuario
        JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.");

        // Limpiar los campos para un nuevo ingreso
        nameField.setText("");
        priceField.setText("");
        stockField.setText("");

        // Actualizar la visualización de la lista
        updateProductDisplay();
    }

    // Recorre la lista de productos y crea un texto con sus datos,
    private void updateProductDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Product p : productList) {
            sb.append("Nombre: ").append(p.getName())
                    .append(" | Precio: ").append(p.getPrice())
                    .append(" | Stock: ").append(p.getStock())
                    .append("\n");
        }
        productDisplay.setText(sb.toString());
    }

    // Crea un archivo Excel en memoria con Apache POI,
    private void exportToExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Crear hoja de cálculo y fila de encabezados
            XSSFSheet sheet = workbook.createSheet("Productos");
            int rowCount = 0;
            Row header = sheet.createRow(rowCount++);
            header.createCell(0).setCellValue("Nombre");
            header.createCell(1).setCellValue("Precio");
            header.createCell(2).setCellValue("Stock");

            // Escribir cada producto en una fila nueva
            for (Product p : productList) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(p.getName());
                row.createCell(1).setCellValue(p.getPrice());
                row.createCell(2).setCellValue(p.getStock());
            }

            // Convertir workbook a un arreglo de bytes para cifrado
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] workbookBytes = bos.toByteArray();

            // Configurar el sistema de archivos de POI y el cifrador
            POIFSFileSystem fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor enc = info.getEncryptor();
            enc.confirmPassword("1234"); // Contraseña incorporada

            // Escribir los bytes cifrados en el sistema de archivos
            try (OutputStream os = enc.getDataStream(fs)) {
                os.write(workbookBytes);
            }

            // Guardar el archivo cifrado en disco
            try (FileOutputStream fos = new FileOutputStream("excelExportado.xlsx")) {
                fs.writeFilesystem(fos);
            }

            // Mensaje de finalización
            JOptionPane.showMessageDialog(this, "Archivo Excel generado con éxito.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al generar Excel: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Punto de entrada de la aplicación: arranca la interfaz en el hilo de Swing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp());
    }
}
