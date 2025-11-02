# 💬 ChatsApp

A lightweight, real-time chat application built with Java Swing that demonstrates multi-window messaging with a custom linked list data structure implementation.

![Java](https://img.shields.io/badge/Java_11-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![FlatLaf](https://img.shields.io/badge/FlatLaf-3.6.1-blue?style=for-the-badge)
![Apache Ant](https://img.shields.io/badge/Apache_Ant-A81C7D?style=for-the-badge&logo=apache&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![MVC](https://img.shields.io/badge/Architecture-MVC-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-Educational-yellow?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

## 📋 Overview

ChatsApp is a desktop messaging application that allows multiple chat windows to communicate with each other in real-time. Built as a demonstration of the MVC (Model-View-Controller) architecture pattern, it features a custom implementation of a linked list data structure for managing chat windows.

**Technical Specifications:**
- **Java Version**: Java 11 (JDK 11)
- **UI Framework**: Java Swing with FlatLaf 3.6.1
- **Build System**: Apache Ant
- **IDE**: NetBeans (optimized)
- **Architecture**: MVC Pattern with custom data structures

## ✨ Features

### Core Functionality
- **Multi-Window Chat**: Create and manage multiple chat windows simultaneously
- **Real-Time Messaging**: Messages are instantly broadcast to all active chat windows
- **User Customization**: Edit and personalize user names for each chat window via dedicated rename dialog
- **Dynamic Window Management**: Add new chat participants on-the-fly with the "+" button
- **Automatic User Naming**: New windows are automatically assigned sequential user names (User 1, User 2, etc.)

### Technical Features
- **Custom Data Structure**: Fully implemented linked list data structure for efficient window management
- **Modern UI**: Clean, professional interface powered by FlatLaf Mac Light theme
- **Event-Driven Architecture**: Responsive GUI with ActionListener implementations
- **Graceful Cleanup**: Automatic window removal on close to prevent memory leaks
- **MVC Architecture**: Well-organized codebase following software design best practices
- **Message Differentiation**: Distinguishes between own messages ("Me:") and others' messages

## 🏗️ Architecture

The application follows the **Model-View-Controller (MVC)** design pattern:

### MVC Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         APPLICATION                              │
│                                                                  │
│  ┌────────────┐         ┌──────────────┐         ┌───────────┐ │
│  │   MODEL    │         │ CONTROLLER   │         │   VIEW    │ │
│  ├────────────┤         ├──────────────┤         ├───────────┤ │
│  │            │         │              │         │           │ │
│  │ LinkedList │◄────────│ChatController│────────►│ChatWindow │ │
│  │            │         │              │         │           │ │
│  │  - Node    │         │ - addWindow()│         │ - display │ │
│  │  - first   │         │ - remove()   │         │ - input   │ │
│  │  - add()   │         │ - send()     │         │ - events  │ │
│  │  - remove()│         │ - count()    │         │           │ │
│  │  - size()  │         │              │         ├───────────┤ │
│  │  - toArray│         │              │         │  Rename   │ │
│  │            │         │              │         │  Window   │ │
│  └────────────┘         └──────────────┘         └───────────┘ │
│       ▲                        │                        │       │
│       │                        │                        │       │
│       │                        ▼                        ▼       │
│       │                  Manages List              User Actions │
│       │                  of Windows               (Send, Edit)  │
│       │                        │                        │       │
│       └────────────────────────┴────────────────────────┘       │
│                        Data Flow Loop                           │
└─────────────────────────────────────────────────────────────────┘
```

### Package Structure

```
src/chatApp/
├── Main.java                    # Entry point, initializes FlatLaf & first window
│
├── controller/
│   └── ChatController.java      # Central message broker & window manager
│
├── model/
│   └── LinkedList.java          # Custom linked list implementation
│                                  with inner Node class
│
└── view/
    ├── ChatWindow.java          # Main chat interface (JFrame)
    ├── ChatWindow.form          # NetBeans visual design file
    ├── RenameWindow.java        # Name editor dialog (JDialog)
    └── RenameWindow.form        # NetBeans visual design file
```

### Data Flow Architecture

```
┌──────────────┐
│  User Types  │
│   Message    │
└──────┬───────┘
       │
       ▼
┌─────────────────────┐
│  ChatWindow (View)  │
│  - Captures input   │
│  - Triggers event   │
└──────┬──────────────┘
       │
       ▼
┌──────────────────────────────┐
│  ChatController              │
│  - Receives message & sender │
│  - Gets all windows from     │
│    LinkedList (Model)        │
│  - Broadcasts to each window │
└──────┬───────────────────────┘
       │
       ▼
┌────────────────────────────┐
│  All ChatWindows (Views)   │
│  - Display message         │
│  - Auto-scroll to bottom   │
│  - Format: "Me:" or "User:"│
└────────────────────────────┘
```

### Class Relationship Diagram

```
              ┌──────────────┐
              │    Main      │
              │ (Entry Point)│
              └──────┬───────┘
                     │ creates
                     ▼
              ┌──────────────────┐
              │ ChatController   │◄───────────┐
              │                  │            │
              │ - LinkedList     │            │ manages
              │ - addWindow()    │            │
              │ - removeWindow() │            │
              │ - sendMessage()  │            │
              └────┬─────────────┘            │
                   │                          │
                   │ manages                  │
                   ▼                          │
            ┌──────────────┐                  │
            │  LinkedList  │                  │
            │   (Model)    │                  │
            │              │                  │
            │ - Node first │                  │
            │ - add()      │                  │
            │ - remove()   │                  │
            │ - toArray()  │                  │
            └──────────────┘                  │
                   │ contains                 │
                   ▼                          │
            ┌──────────────┐                  │
            │  ChatWindow  │──────────────────┘
            │   (View)     │
            │              │
            │ - controller │ (reference)
            │ - userName   │
            │ - sendBtn    │
            │ - textArea   │
            └──────┬───────┘
                   │ opens
                   ▼
            ┌──────────────┐
            │RenameWindow  │
            │   (View)     │
            │              │
            │ - nameInput  │
            │ - renameBtn  │
            └──────────────┘
```

### Key Components

#### Controller Layer
- **ChatController**: Orchestrates message broadcasting between multiple chat windows
  - Manages window lifecycle (add/remove)
  - Handles message routing to all active windows
  - Tracks total window count for auto-naming

#### Model Layer
- **LinkedList**: Custom doubly-linked data structure implementation
  - `add()`, `addFirst()`, `addLast()` - Multiple insertion methods
  - `remove()` - Smart removal by reference equality
  - `toArray()` - Conversion for iteration
  - `size()` - Dynamic count tracking
  - Inner `Node` class for list structure

#### View Layer
- **ChatWindow**: Individual chat window (main interface)
  - Message display area with auto-scroll
  - Text input field with send button
  - User identification header with editable name
  - Add new chat window button
  - Window close cleanup integration
  
- **RenameWindow**: Modal dialog for name editing (350x200px)
  - Simple text input interface
  - Confirmation and cancellation options
  - Instant name update on confirmation

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher (project compiled with JDK 11)
- **NetBeans IDE**: Recommended for easy project setup (or any Java IDE)
- **Apache Ant**: For building the project (usually bundled with NetBeans)

### Dependencies

The project includes these external libraries in the `lib/` folder:

| Library | Version | Purpose |
|---------|---------|---------|
| `flatlaf-3.6.1.jar` | 3.6.1 | Modern Look and Feel for Swing applications |
| `flatlaf-intellij-themes-3.6.1.jar` | 3.6.1 | Additional IntelliJ-based themes (Mac Light theme used) |

These libraries are **included in the repository** and will be automatically packaged with the application.

### Installation

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd Chat-App
   ```

2. **Open in NetBeans**
   - Launch NetBeans IDE
   - File → Open Project
   - Navigate to the `Chat-App` folder and select it

3. **Build the project**
   ```bash
   ant clean
   ant build
   ```

4. **Run the application**
   ```bash
   ant run
   ```
   
   Or simply press `F6` in NetBeans to run the project.

## 🎮 Usage

### Basic Operations

1. **Launch the Application**: Run the `Main.java` class to start the application
   - First window opens automatically as "User 1"
   
2. **Create New Chat Windows**: Click the `+` button in the top-right corner
   - Each new window gets a sequential name (User 2, User 3, etc.)
   - All windows remain synchronized
   
3. **Send Messages**: 
   - Type your message in the "Enter Message" field
   - Click "Send" button or press Enter
   - Message appears instantly in all open windows
   
4. **Rename Users**: 
   - Click the "Edit" button next to your username
   - Enter new name in the dialog
   - Click "Rename" to confirm or "Cancel" to abort
   
5. **View Messages**: 
   - All messages appear in the scrollable chat area
   - Your messages show as "Me: [message]"
   - Others' messages show as "[Username]: [message]"
   - Chat area auto-scrolls to latest message
   
6. **Close Windows**:
   - Click the X button to close any window
   - Window is automatically removed from chat system
   - Other windows continue functioning normally

### Message Flow

```
User A sends message → ChatController → Broadcast to all windows
                                      ↓
                         User A sees "Me: [message]"
                         Other users see "User A: [message]"
```

### UI Layout

```
┌─────────────────────────────────────────┐
│ Howdy, User 1    [Edit] [+]            │  ← Header (Green)
│ CHATSAPP                                 │
├─────────────────────────────────────────┤
│ Me: Hello everyone                      │
│                                         │  ← Chat Area
│ User 2: Hi there!                       │     (Scrollable)
│                                         │
│ User 3: Welcome!                        │
├─────────────────────────────────────────┤
│ Enter Message                           │
│ [___________________________] [Send]    │  ← Input Area
└─────────────────────────────────────────┘
```

## 📁 Project Structure

```
Chat-App/
├── src/                          # Source code
│   └── chatApp/
│       ├── Main.java             # Application entry point
│       ├── controller/           # Controller layer
│       ├── model/                # Data structures and models
│       └── view/                 # UI components
├── lib/                          # External libraries (JAR files)
├── build/                        # Compiled classes (ignored by Git)
├── nbproject/                    # NetBeans project files
├── build.xml                     # Ant build configuration
├── .gitignore                    # Git ignore rules
└── README.md                     # This file
```

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java SE** | 11 | Core programming language |
| **Java Swing** | Built-in | GUI framework for desktop applications |
| **FlatLaf** | 3.6.1 | Modern Look and Feel (Mac Light theme) |
| **FlatLaf IntelliJ Themes** | 3.6.1 | Extended theme support |
| **Apache Ant** | - | Build automation and project management |
| **NetBeans** | - | Integrated Development Environment |

### Key Java APIs Used
- `javax.swing.*` - GUI components (JFrame, JDialog, JButton, JTextArea, etc.)
- `java.awt.*` - Layout managers and event handling
- `java.awt.event.ActionListener` - Button click event handling
- `java.awt.event.WindowAdapter` - Window close event handling

## 🎯 Learning Objectives

This project demonstrates essential software engineering concepts:

### Design Patterns & Architecture
- ✅ **MVC (Model-View-Controller)** architecture implementation
- ✅ **Observer pattern** for message broadcasting
- ✅ **Singleton-like controller** managing multiple views

### Data Structures & Algorithms
- ✅ **Custom Linked List** implementation from scratch
- ✅ Node-based data structure design
- ✅ Understanding of O(n) vs O(1) operations
- ✅ Array conversion and iteration techniques

### GUI Development
- ✅ **Event-driven programming** with Swing
- ✅ Multi-window desktop application coordination
- ✅ **Modal dialogs** (JDialog) for user input
- ✅ **Layout managers** (GroupLayout)
- ✅ Component event handling (ActionListener, WindowListener)

### Software Engineering Practices
- ✅ Separation of concerns (Model/View/Controller)
- ✅ Memory management (proper window cleanup)
- ✅ User experience design (auto-scroll, message formatting)
- ✅ Modern UI theming with third-party libraries

### Java Concepts
- ✅ Package organization
- ✅ Object-oriented programming (encapsulation, inheritance)
- ✅ Reference vs. value comparison
- ✅ Inner classes (Node in LinkedList)

## 🔧 Development

### Building from Source

```bash
# Clean previous builds
ant clean

# Compile the project
ant compile

# Build JAR file
ant jar

# Run the application
ant run
```

### Build Output

- **Compiled Classes**: `build/classes/`
- **Distributable JAR**: `dist/Chat-App.jar`
- **Javadoc**: `dist/javadoc/` (if generated)

### Project Configuration

The project uses the following build configurations:

- **Source Encoding**: UTF-8
- **JAR Compression**: Disabled (for faster builds)
- **Main Class**: `chatApp.Main`
- **Classpath**: Includes FlatLaf libraries from `lib/` folder

### Adding New Features

1. **Model Layer**: Add data structures in `src/chatApp/model/`
   - Example: Add a message history storage system
   
2. **View Layer**: Create new UI components in `src/chatApp/view/`
   - Use NetBeans GUI Builder (.form files) for visual design
   - Example: Add emoji picker, file sharing dialog
   
3. **Controller Layer**: Implement business logic in `src/chatApp/controller/`
   - Example: Add message filtering, user authentication

### NetBeans Form Files

The project uses NetBeans GUI Builder:
- `ChatWindow.form` - Main chat interface design
- `RenameWindow.form` - Rename dialog design

**Note**: Editing `.form` files requires NetBeans IDE or manual XML editing.

## 📝 Code Highlights

### Custom Linked List Implementation

The project includes a **fully functional linked list** data structure built from scratch:

```java
public class LinkedList {
    private Node first;
    
    // Core operations
    - add(ChatWindow)         // Append to end
    - add(int, ChatWindow)    // Insert at index
    - addFirst(ChatWindow)    // Prepend
    - addLast(ChatWindow)     // Append (alias)
    - remove(ChatWindow)      // Remove by reference
    - toArray()               // Convert to array
    - size()                  // Get count
    
    // Inner Node class
    class Node {
        ChatWindow chatWindow;
        Node next;
    }
}
```

**Key Features:**
- Reference-based removal (uses `.equals()` for comparison)
- Index-based insertion with bounds checking
- O(1) add at beginning, O(n) add at end
- Array conversion for easy iteration

### Message Broadcasting

Messages are efficiently broadcast to all connected windows using the **Observer-like pattern**:

```java
public void sendMessage(String message, ChatWindow sender) {
    ChatWindow[] windows = chatWindowList.toArray();
    for (ChatWindow window : windows) {
        if (window == sender) {
            window.setMessage("Me : " + message);
        } else {
            window.setMessage(sender.getUserName() + " : " + message);
        }
    }
}
```

**Benefits:**
- Clean separation of concerns (MVC)
- Single point of message routing
- Different message formatting for sender vs. receivers
- No direct coupling between chat windows

### Window Lifecycle Management

Automatic cleanup on window close:

```java
// In ChatWindow.java
private void formWindowClosing(WindowEvent evt) {
    chatController.removeChatWindow(this);
}
```

Prevents memory leaks and maintains accurate window count.

## 🤝 Contributing

Contributions are welcome! Feel free to:

- 🐛 Report bugs
- 💡 Suggest new features
- 🔧 Submit pull requests
- 📖 Improve documentation

### Potential Enhancements

- [ ] Network support (socket-based chat over LAN/Internet)
- [ ] Message persistence (save chat history to file/database)
- [ ] Emoji support and rich text formatting
- [ ] File sharing capabilities
- [ ] User avatars and profile pictures
- [ ] Timestamp for each message
- [ ] Private messaging between specific users
- [ ] Message search and filtering
- [ ] Sound notifications for new messages
- [ ] Theme customization options
- [ ] Typing indicator ("User X is typing...")
- [ ] Message read receipts

## � Known Limitations

- **Local-only**: Chat windows only communicate within the same application instance (no network support)
- **No Persistence**: Messages are lost when the application closes
- **Single Machine**: All chat windows must run on the same computer
- **No User Authentication**: No login system or user verification
- **Memory-based**: All data stored in RAM (no database)

## �📄 License

This project is open-source and available for educational purposes.

## 👨‍💻 Author

Developed as part of **ICD119 coursework** at WEEKDAY program.

## 🙏 Acknowledgments

- **[FlatLaf](https://www.formdev.com/flatlaf/)** (v3.6.1) - Modern Look and Feel for Java Swing
- **NetBeans IDE** - Excellent visual GUI builder and development environment
- **Java Swing Documentation** - Comprehensive API reference
- **Oracle Java Tutorials** - Foundation for understanding Swing architecture

## 📚 Resources

### Learn More About Technologies Used

- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [FlatLaf Documentation](https://www.formdev.com/flatlaf/)
- [MVC Pattern Explained](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
- [Linked List Data Structure](https://en.wikipedia.org/wiki/Linked_list)
- [NetBeans Platform](https://netbeans.apache.org/)

---

**Note**: This application is designed for **local machine use** and serves as an educational demonstration. For network-based chat functionality, consider implementing:
- Socket programming (`java.net.Socket`)
- WebSocket protocol
- Messaging systems (MQTT, RabbitMQ)
- RESTful API with polling
- Java RMI (Remote Method Invocation)

**Perfect for**: Learning Java Swing, understanding MVC architecture, practicing data structure implementation, and building portfolio projects! 🚀
