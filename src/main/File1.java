/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author tahir hussain
 */
public class File1 {

    public static File createFile(File txtFile, File javaFile) {

        File file = null;
        String javaFilePath = javaFile.getAbsolutePath();
        String javaFileName = javaFile.getName();

        try {
            if (Files.deleteIfExists(Paths.get(javaFile.getAbsolutePath()))) {
                new File(javaFilePath).createNewFile();
            }
            javaFile = new File(javaFilePath);
            copyTo(txtFile, javaFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaFile;
    }

    public static void copyTo(File read, File write) {

        try {
            FileWriter writer = new FileWriter(write);

            BufferedReader buf = new BufferedReader(new FileReader(read));
            String line = " ";

            while (line != null) {
                line = buf.readLine();
                //System.out.println("copyTo"+line);
                if (line != null) {
//                    System.out.println(write.getAbsoluteFile());
//                    if (line.indexOf("JFrameCode") > 0) {
                    line = line.replace("JFrameCode", write.getName().replace(".java", ""));
//                    }
                    writer.write(String.format(line + "\n"));

                }

            }

            writer.close();
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] a) {
//C:/Users/tahir hussain/NewProject/JDBS
        File f = File1.createFile(
                new File("C:/Users/tahir hussain/NewProject/JDBS/src/main/frame1"),
                new File("C:/Users/tahir hussain/NewProject/JDBS/src/main/St.java"));

        getFile(new File("C:/Users/tahir hussain/NewProject/JDBS/src/main/St.java"));

        //writeFile(new File("C:/Users/tahir hussain/NewProject/JDBS/src/main/St.java").getAbsolutePath(), ".", "c", "d");
    }

    public static boolean writeFile(String write1, String atImport, String constr, String atComponent, String atDeclare) {
        
        //THIS COLOR CAN BE CUSTOMIZED
        // due to lack of time I couldn't be able to proceed this, you have right to modify it 
        String panelColor = "53,59,72";
        
        FileWriter writer = null;
        File file = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFile(new File(write1))));
            writer = new FileWriter(new File(write1));
            String line = "";

            if (atImport != null) {
                while ((line = reader.readLine()) != null) {

                    if (line.indexOf("package") != -1) {
                        break;
                    }
                    writer.write(String.format(line + "\n"));
                    //System.out.println("----> "+line);
                }

                writer.write(line + "\n" + atImport + "\n");
            }

            while ((line = reader.readLine()) != null) {
//                        line=line.replace("Student","Student2");
                if (line.indexOf("getContentPane().setLayout(") > 0) {
                    writer.write(line + "\n");
                    writer.write(String.format(atComponent + "\n"));
                } else if (line.contains("initComponents();")) {
                    //System.out.println("initCompooooon");
                    writer.write(String.format(constr));
                    //writer.write(String.format(line.replace("initComponents();", constr)));
                } else if (line.contains("//.")) {
                    writer.write(String.format(atDeclare) + "\n");
                    writer.write(line + "\n");
                } else {
                    writer.write(String.format(line.replace("PANEL_COLOR", panelColor) + "\n"));
                }

            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
    public static boolean writeFile(String write1, String atImport, String atComponent, String atDeclare) {
        String panelColor = "Color.BLACK";
        FileWriter writer = null;
        File file = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFile(new File(write1))));
            writer = new FileWriter(new File(write1));
            String line = "";

            String sb=new String();
                while ((line = reader.readLine()) != null) {
                    sb=sb.concat(String.format("%s %n" ,line));
            }
                
                sb=sb.replace(line, atComponent);
                sb=sb.replace(line, atComponent);
                sb=sb.replace(line, atComponent);
                writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
     */
    public static File getFile(File source) {

        String path = new File("File1.java").getAbsolutePath().replace("\\", "/");

        path = path.replace("/File1.java", "");

        System.out.println("Path is:" + path);
        File dest = new File(path + "/src/main/file.txt");
        try {
            if (dest.exists()) {
                System.out.println("file exists");
                dest.delete();
            }
            dest.createNewFile();

            //System.out.println(source.toPath() +"  ---> "+ dest.toPath());
            //Files.copy(source.toPath(), dest.toPath());
            System.out.println(source);
            System.out.println(dest);

            copyTo(source, dest);

            //System.out.println("New File has been created");
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } //        catch(EOFException e){
        //        e.printStackTrace();
        //        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        return dest;
    }

    private static void copyXML(File thisProjectFile, File newProjectFile) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docFactory.newDocumentBuilder();
            Document document = builder.parse(thisProjectFile);
            TransformerFactory transformerFact = TransformerFactory.newInstance();
            Transformer transformer = transformerFact.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult destine = new StreamResult(newProjectFile);
            transformer.transform(source, destine);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(String filePath) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            String la = "";

            while ((la = bf.readLine()) != null) {
                System.out.println(la);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public static List<String> getFileNameFrom(File file) {
        List<String> fileName = new ArrayList<>();
        try {
            File file1 = file;

            System.out.println(file1.getCanonicalPath());
            System.out.println(file1.getCanonicalPath());
            String[] list = file1.list();

            for (String f : list) {
                File file3 = new File(f);
                if (file3.isDirectory()) {
                    getFileNameFrom(file3);
                }
                System.out.println(file3.getName());

                File[] temp = file3.listFiles(new FilterFile(".java"));
                for (File a : temp) {
                    fileName.add(a.getName());
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fileName;
    }

    public static List<String> getFileNameFrom(File file, List<String> fileName) {
        try {
            File file1 = file;

            System.out.println(file1.getCanonicalPath());
//		System.out.println(file1.getCanonicalPath());
            File[] files = file1.listFiles();

            for (File f : files) {

                //File file3=new File(f);
                if (f.isDirectory()) {
                    System.out.println("is Directory");
                    getFileNameFrom(f, fileName);
                }
                //	System.out.println(f.getName());

                //System.out.println("file: ");
                File[] temp = f.listFiles(new FilterFile(".java"));

                if (temp != null) {
                    for (File a : temp) {
                        fileName.add(a.getName());
                    }
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fileName;
    }

    public static void replaceFileContent(File file, String toReplace, String beplaced) {

        if (file.exists()) {

            FileWriter fw = null;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(String.format(line + "\n"));
                }
                sb.replace(sb.indexOf(toReplace), sb.indexOf(toReplace) + toReplace.length(), beplaced);

                fw = new FileWriter(file);
                fw.write(sb.toString());
                fw.close();
            } catch (IOException e) {

            }

        }

    }

    public static File textToJava(File file, String destine) throws FileNotFoundException {

        if (!file.exists()) {
            throw new FileNotFoundException("fILE NOT FOUND");
        }

        BufferedReader br = new BufferedReader(new FileReader(file));

        try {
            String line = "";
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(String.format("%s \n", line));
            }
            System.out.println("filename:" + file.getName());
            String javaFile = Functions.getJcName(file.getName() + ".java");

            file = new File(destine + javaFile);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter fw = new FileWriter(file)) {
                fw.write(sb.toString());
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void createDirectories(String path) {
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void createDirectory(String path) {
        File file = new File(path);

        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void createDirectoriesAndFiles(File thisProject, File newProject, String replaceFileContent, String beReplaced, String dontCreate) {
        //copyFileWithReplace(thisProject, newProject, replaceFileContent, beReplaced, dontCreate);
        copyFileWithReplace(thisProject, newProject, replaceFileContent, beReplaced, dontCreate);
    }

    /*
    
    public static void copyFileWithReplace(File thisProjectFile, File newProjectFile, String toReplace, String beReplace, String dontCreate) {
        createDirectories(newProjectFile.getAbsolutePath());
        if (thisProjectFile.exists()) {
            File[] files = thisProjectFile.listFiles(new ExcludeFilterFile(".class"));
            for (File file : files) {

                System.out.println("file Name " + file.getName());
                if (!file.isDirectory()) {
                    if (thisProjectFile.exists()) {

                        try {
                            FileWriter writer = new FileWriter(newProjectFile + "/" + file.getName());

                            BufferedReader buf = new BufferedReader(new FileReader(file));
                            String line = " ";
                            String toR = toReplace.toLowerCase();
                            String beR = beReplace.toLowerCase();
                            while ((line = buf.readLine()) != null) {

                                if (line.contains(toR)) {
                                    line = line.replace(toR, beR);
                                }

                                if (line.contains(toReplace)) {
                                    line = line.replace(toReplace, beReplace);
                                }

                                writer.write(String.format("%s \n", line));

                            }

                            writer.close();
                            System.out.println(file.getName() + " file is copied into the " + newProjectFile.getAbsolutePath());
                            buf.close();

                        } catch (UnknownFormatConversionException e) {
                            System.out.println("Exception " + e);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerExceptioin " + e);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    if (!file.getName().equals(dontCreate)) {
                        System.out.println("New Folder " + file.getName() + " is Opened \n");
                        copyFileWithReplace(
                                file, new File(newProjectFile.getAbsolutePath() + "/" + file.getName()),
                                toReplace, beReplace, dontCreate);
                        System.out.println("Folder and its file are created\n ");
                    }

                }
            }
        }
    }
     */
    // it is time consuming code but it is precise hence I used it instead of above code
    public static void copyFileWithReplace(File thisProjectFile, File newProjectFile, String toReplace, String beReplaced, String dontCreate) {
        createDirectories(newProjectFile.getAbsolutePath());
        if (thisProjectFile.exists()) {
            File[] files = thisProjectFile.listFiles(new ExcludeFilterFile(".class"));
            for (File file : files) {
                System.out.println("file Name " + file.getName());
                if (!file.isDirectory()) {
                    if (thisProjectFile.exists()) {
                        try {
                            System.out.println();
                            if (file.getName().equals("build.xml")) {
                                System.out.println("build");
                                System.out.println("file name: "+newProjectFile);
                                 buildXMLFile(file, new File(newProjectFile.getAbsolutePath()+"/"+file.getName()), toReplace, beReplaced);
                            } else if (file.getName().equals(   "build-impl.xml" )) {
                                System.out.println("build-impl");
                                System.out.println("file name: "+newProjectFile);
                                buildImplXML(file, new File(newProjectFile.getAbsolutePath()+"/"+file.getName()),toReplace, beReplaced);
                            } else if (file.getName().endsWith(".properties")) {
                                System.out.println("");
                                System.out.println("file name: "+newProjectFile);
                                copyPropertiesFile(file, new File(newProjectFile.getAbsolutePath()+"/"+file.getName()),beReplaced);
                            }else if(file.getName().equals("project.xml")){
                                projectXML(file, new File(newProjectFile.getAbsolutePath()+"/"+file.getName()),beReplaced);
                            }
                            
                            else if (file.getName().endsWith(".xml")) {
                                System.out.println("");
                                System.out.println("file name: "+newProjectFile);
                                copyXML(file, new File(newProjectFile.getAbsolutePath()+"/"+file.getName()));
                            }else{
                            BufferedOutputStream bou = new BufferedOutputStream(new FileOutputStream(newProjectFile + "/" + file.getName()));
                            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
                            Integer line = -1;
                            byte[] b = new byte[1024];
                             while ((line = bin.read(b)) != -1) {
                                 bou.write(b);
                            }
                            //writer.close();
                            System.out.println(file.getName() + " file is copied into the " + newProjectFile.getAbsolutePath());
                            bou.close();
                            }
                        } catch (UnknownFormatConversionException e) {
                            System.out.println("Exception " + e);
                        } catch (NullPointerException e) {
                            System.out.println("NullPointerExceptioin " + e);
                        }catch(IOException e){
                        e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("New Folder " + file.getName() + " is Opened \n");
                    if (!file.getName().equals(dontCreate)) {
                        copyFileWithReplace(
                                file, new File(newProjectFile.getAbsolutePath() + "/" + file.getName()), toReplace, beReplaced, dontCreate
                        );
                    }
                    System.out.println("Folder and its file are created\n ");

                }
            }
        }
    }

    public static void copyFileWithReplaceBytes(File thisProjectFile, File newProjectFile, String toReplace, String beReplaced, String dontCreate) {
        createDirectories(newProjectFile.getAbsolutePath());
        if (thisProjectFile.exists()) {
            File[] files = thisProjectFile.listFiles(new ExcludeFilterFile(".class"));
            for (File file : files) {
                System.out.println("file Name " + file.getName());
                if (!file.isDirectory()) {
                    if (thisProjectFile.exists()) {
                        try {
                            BufferedOutputStream bou = new BufferedOutputStream(new FileOutputStream(newProjectFile + "/" + file.getName()));
                            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));

                            Integer line = -1;
                            byte[] b = new byte[1024];
                            String toR = toReplace.toLowerCase();
                            String beR = beReplaced.toLowerCase();
                            while ((line = bin.read(b)) != -1) {
                                String check = new String(b);
                                if (check.contains(toR)) {
                                    check = check.replace(toR, beR);
                                    b = check.getBytes("UTF-8");
                                }
                                if (check.contains(toReplace)) {
                                    check = check.replace(toReplace, beReplaced);
                                    b = check.getBytes("UTF-8");
                                }
                                bou.write(b);
                            }
                            //writer.close();
                            System.out.println(file.getName() + " file is copied into the " + newProjectFile.getAbsolutePath());
                            bou.close();

                        } catch (UnknownFormatConversionException e) {
                            System.out.println("Exception " + e);
                        } catch (NullPointerException e) {

                            System.out.println("NullPointerExceptioin " + e);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("New Folder " + file.getName() + " is Opened \n");
                    if (!file.getName().equals(dontCreate)) {
                        copyFileWithReplace(
                                file, new File(newProjectFile.getAbsolutePath() + "/" + file.getName()), toReplace, beReplaced, dontCreate
                        );
                    }
                    System.out.println("Folder and its file are created\n ");

                }
            }
        }

    }

    /*
    public static void createDirectoriesAndFiles(File thisProjectFile, File newProjectFile) {
        createDirectories(newProjectFile.getAbsolutePath());
        if (thisProjectFile.exists()) {
            File[] files = thisProjectFile.listFiles(new ExcludeFilterFile(".class"));
            for (File file : files) {
                System.out.println("file Name " + file.getName());
                if (!file.isDirectory()) {
                    if (thisProjectFile.exists()) {
                        try {
                            FileWriter writer = new FileWriter(newProjectFile + "/" + file.getName());
                            BufferedReader buf = new BufferedReader(new FileReader(file));
                            String line = " ";
                            while ((line = buf.readLine()) != null) {
                                writer.write(String.format(line+"\n"));
                            }

                            writer.close();
                            System.out.println(file.getName() + " file is copied into the " + newProjectFile.getAbsolutePath());
                            buf.close();

                        } catch (UnknownFormatConversionException e) {
                            System.out.println("Exception " + e);
                        } catch (NullPointerException e) {

                            System.out.println("NullPointerExceptioin " + e);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("New Folder " + file.getName() + " is Opened \n");
                    createDirectoriesAndFiles(
                            file, new File(newProjectFile.getAbsolutePath() + "/" + file.getName())
                    );
                    System.out.println("Folder and its file are created\n ");

                }
            }
        }

    }
     */
    public static void createDirectoriesAndFiles(File thisProjectFile, File newProjectFile) {
        createDirectories(newProjectFile.getAbsolutePath());
        if (thisProjectFile.exists()) {

            File[] files = thisProjectFile.listFiles(new ExcludeFilterFile(".class"));
            for (File file : files) {

                System.out.println("file Name " + file.getName());
                if (!file.isDirectory()) {
                    if (thisProjectFile.exists()) {
                        try {
                            //BufferedOutputStream bou = new BufferedOutputStream(new FileOutputStream(newProjectFile + "/" + file.getName()));
                            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
                            Integer line = -1;
                            byte[] b = new byte[20];

                            while ((line = bin.read(b)) != -1) {
                                //writer.write(String.format(line+"\n"));

                            }

                            //writer.close();
                            System.out.println(file.getName() + " file is copied into the " + newProjectFile.getAbsolutePath());
                            //buf.close();

                        } catch (UnknownFormatConversionException e) {
                            System.out.println("Exception " + e);
                        } catch (NullPointerException e) {

                            System.out.println("NullPointerExceptioin " + e);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("New Folder " + file.getName() + " is Opened \n");
                    createDirectoriesAndFiles(
                            file, new File(newProjectFile.getAbsolutePath() + "/" + file.getName())
                    );
                    System.out.println("Folder and its file are created\n ");

                }
            }
        }

    }

    public static StringBuilder getFilePath(File newProject, StringBuilder sb) {

        if (newProject != null && newProject.exists()) {
            File[] files = newProject.listFiles();
            System.out.println(files.length);
            for (File file : files) {
                System.out.println("file Name " + file.getName());
                if (!file.isDirectory()) {
                    if (file.exists()) {
                        try {
                            if (file.getName().contains(".java")) {
                                sb.append("<file>file:" + file.getAbsolutePath().replace(" ", "%20") + "\n");
                            }
                            //System.out.println("-------------> "+sb);
                        } catch (UnknownFormatConversionException e) {
                            System.out.println("Exception " + e);
                        } catch (NullPointerException e) {

                            System.out.println("NullPointerExceptioin " + e);
                        }
                        // System.out.println("...");
                    }
                } else {
                    System.out.println("new folder is opened " + file);
                    getFilePath(file, sb);
                }
            }
        }
        return sb;
    }

    /*
    public static void writeWithReplace(File file, String at, String bePlaced)  {
         
            try {
            BufferedOutputStream bou = new BufferedOutputStream(new FileOutputStream(file));
            
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(getFile(file)));

            byte[] line = new byte[1024];
            Integer length = line.length;
            
            while (bin.read(line) != -1) {
                String a = new String(line);
                if (a.contains(at)) {
                    bou.write(bePlaced.getBytes());
                }
                
                System.out.println("================== >>> "+a);
                bou.write(line);
            }
            
        bou.close();
        bin.close();
            } catch (FileNotFoundException ex) {
            Logger.getLogger(File1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
    } 
    
    }

     */
    public static void writeWithReplace(File file, String at, String bePlaced) {

        try {
            BufferedReader bin = new BufferedReader(new FileReader(getFile(file)));
            FileWriter bou = new FileWriter(file);

//            byte[] line = new byte[1024];
//            Integer length = line.length;
            String line = "";

            while ((line = bin.readLine()) != null) {
                if (line.contains(at)) {
                    bou.write(bePlaced);
                }

                bou.write(String.format("%s \n", line));
            }

            bou.close();
            bin.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

//    public static void readFromXML(File file) {
//
//        try {
//            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = builderFactory.newDocumentBuilder();
//            Document doc = builder.parse(file);
//            doc.getElementsByTagName("name");
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    public static void copyPropertiesFile(File file, File newFile, String projectName) {
            System.out.println("---->"+newFile.getAbsolutePath());
        
            Properties properties = new Properties();
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            properties.load(buf);
            Properties newPr = new Properties();
            properties.forEach((k, v) -> {
                if (k.equals("main.class")) {
                    newPr.setProperty(String.valueOf(k), projectName.toLowerCase() + "." + projectName);
                } else if (k.equals("dist.jar")) {
                    newPr.setProperty(String.valueOf(k), "${dist.dir}/" + projectName + ".jar");
                } else {
                    newPr.setProperty(String.valueOf(k), String.valueOf(v));
                }
            });

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            BufferedOutputStream bou = new BufferedOutputStream(new FileOutputStream(newFile));
            newPr.store(bou, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buildXMLFile(File file, File newFile, String toReplace, String beReplaced) {
System.out.println("AbsolutePath is:--->"+newFile.getAbsolutePath());
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            Node project = document.getElementsByTagName("project").item(0);
            NamedNodeMap attr = project.getAttributes();
            Node nodeAttr = attr.getNamedItem("name");
            nodeAttr.setTextContent(beReplaced);
            NodeList list = project.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if ("description".equals(element.getNodeName())) {
                        String e = element.getTextContent();
                        element.setTextContent(e.replace(toReplace, beReplaced));
                    }
                }
                if (node.getNodeType() == Node.COMMENT_NODE) {
                    Comment com = (Comment) node;
                    Comment comment = (Comment) list.item(i);
                    comment.setData(comment.getData().replace(toReplace, beReplaced));
                }
            }
            TransformerFactory transformerFactory
                    = TransformerFactory.newInstance();
            Transformer transformer
                    = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(newFile);
            transformer.transform(source, result);

//            //displaying 
//            StreamResult out = new StreamResult(System.out);
//            transformer.transform(source, out);
//        
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException ex) {
            Logger.getLogger(File1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void projectXML(File file, File newFile, String appName) {
        try {
            System.out.println("---->"+newFile.getAbsolutePath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            Node project = document.getElementsByTagName("configuration").item(0);
            NodeList list = project.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if ("data".equals(element.getNodeName())) {
                        System.out.println("Node.Element_node ");
                        NodeList dataChild = element.getChildNodes();
                        for (int j = 0; j < dataChild.getLength(); j++) {
                            Node n = dataChild.item(j);
                            if ("name".equals(n.getNodeName())) {
                                n.setTextContent(appName);
                                System.out.println(n.getNodeName());
                            }
                        }
                    }
                    System.out.println("---> Node Name:" + element.getNodeName());
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(newFile);
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException ex) {
            Logger.getLogger(File1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void buildImplXML(File sourceFile, File destine, String toReplace, String beReplaced) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(sourceFile);
            NodeList docChild = document.getChildNodes();

            Node proj = document.getElementsByTagName("project").item(0);
            NamedNodeMap map = proj.getAttributes();
            Node name = map.getNamedItem("name");
            name.setTextContent(name.getTextContent().replace(toReplace, beReplaced));

            // reading and changing the child nodes of the build-Impl.xml file
            int docIndex = 0;
            int docLength = docChild.getLength();

            while (docIndex < docLength) {
                System.out.println(docChild.item(docIndex).getNodeName());
                if (docChild.item(docIndex).getNodeName().equals("project")) {
                    System.out.println("--project");

                    NodeList proChild = docChild.item(docIndex).getChildNodes();
                    int proIndex = 0;
                    int proLength = proChild.getLength();

                    while (proIndex < proLength) {

                        if (proChild.item(proIndex).getNodeName().equals("target")) {

                            NodeList targetChild = proChild.item(proIndex).getChildNodes();
                            int targetIndex = 0;
                            int targetLength = targetChild.getLength();
                            while (targetIndex < targetLength) {
                                try{
                                if (targetChild.item(targetIndex).getNodeName().equals("macrodef")) {
                                    System.out.println("macrodef");
                                    NodeList macrodef = targetChild.item(targetIndex).getChildNodes();

                                    int macroIndex = 0;
                                    int macroLength = macrodef.getLength();

                                    while (macroIndex < macroLength) {
                                            try{
                                        if (macrodef.item(macroIndex).getNodeName().equals("sequential")) {
                                            System.out.println("sequantial:" + targetIndex);
                                        
                                            
                                            NodeList macroChild=macrodef.item(macroIndex).getChildNodes();
                                            int macroChildIndex=0;
                                            int macroChildL=macroChild.getLength();
                                            
                                            while(macroChildIndex<macroChildL){
                                                
                                                    if(macroChild.item(macroChildIndex).getNodeName().equals("condition")){ 
                                                       NamedNodeMap named=macroChild.item(macroChildIndex).getAttributes();
                                                       Node n=named.getNamedItem("else");
                                                       n.setTextContent(n.getTextContent().replace(toReplace, beReplaced));
                                                       System.out.println("\n============= ");
                                                        System.out.println(n.getTextContent());
                                                        System.out.println("====================");
                                                   }
                                                    
                                                    if(macroChild.item(macroChildIndex).getNodeName().equals("testng")){ 
                                                       NamedNodeMap named=macroChild.item(macroChildIndex).getAttributes();
                                                       Node n=named.getNamedItem("suitename");
                                                       n.setTextContent(n.getTextContent().replace(toReplace, beReplaced));
                                                       System.out.println("\n============= ");
                                                        System.out.println(n.getTextContent());
                                                        System.out.println("====================");
                                                   }
                                                    
                                            macroChildIndex++;
                                            }
                                            
                                            
                                        }
                                            }catch(NullPointerException e){
                                            e.printStackTrace();
                                            }

                                        macroIndex++;
                                    }

                                }

                                if (targetChild.item(targetIndex).getNodeName().equals("echo")) {
                                    System.out.println("echo:" + targetIndex);
                                        NamedNodeMap named=targetChild.item(targetIndex).getAttributes();
                                            Node n=named.getNamedItem("message");
                                            if(n!=null)
                                            n.setTextContent(n.getTextContent().replace(toReplace, beReplaced));
                                
                                }
                                
                                }catch(NullPointerException e){
                                    e.printStackTrace();
                                }
                                targetIndex++;
                            }

                        }
//System.out.println(proIndex);
                        proIndex++;

                    }

                }

                docIndex++;
            }

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult dest = new StreamResult(destine);
            transformer.transform(source, dest);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
