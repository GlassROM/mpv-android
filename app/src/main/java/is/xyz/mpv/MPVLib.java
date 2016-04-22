package is.xyz.mpv;

// Wrapper for native library

import android.util.EventLog;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MPVLib {

     static {
        String[] libs = { "mpv", "player" };
        for (String lib: libs) {
            System.loadLibrary(lib);
        }
     }

     public static native void create();
     public static native void init();
     public static native void destroy();
     public static native void initGL();
     public static native void destroyGL();

     public static native void resize(int width, int height);
     public static native void draw();
     public static native void step();

     public static native void command(String[] cmd);

     public static native int setOptionString(String name, String value);

     public static native int getPropertyInt(String property);
     public static native void setPropertyInt(String property, int value);
     public static native boolean getPropertyBoolean(String property);
     public static native void setPropertyBoolean(String property, boolean value);
     public static native String getPropertyString(String property);
     public static native void setPropertyString(String property, String value);

     public static native void observeProperty(String property, int format);

     private static List<EventObserver> observers = new ArrayList<>();

     public static void addObserver(EventObserver o) {
          observers.add(o);
     }

     public static void clearObservers() {
          observers.clear();
     }

     public static void eventProperty(String property, long value) {
          for (EventObserver o : observers)
               o.eventProperty(property, value);
     }

     public static void eventProperty(String property, boolean value) {
          for (EventObserver o : observers)
               o.eventProperty(property, value);
     }

     public static void eventProperty(String property, String value) {
          for (EventObserver o : observers)
               o.eventProperty(property);
     }

     public static void eventProperty(String property) {
          for (EventObserver o : observers)
               o.eventProperty(property);
     }

     public static void event(int eventId) {
          for (EventObserver o : observers)
               o.event(eventId);
     }

     public enum mpvFormat {
          MPV_FORMAT_NONE(0),
          MPV_FORMAT_STRING(1),
          MPV_FORMAT_OSD_STRING(2),
          MPV_FORMAT_FLAG(3),
          MPV_FORMAT_INT64(4),
          MPV_FORMAT_DOUBLE(5),
          MPV_FORMAT_NODE(6),
          MPV_FORMAT_NODE_ARRAY(7),
          MPV_FORMAT_NODE_MAP(8),
          MPV_FORMAT_BYTE_ARRAY(9);

          private int value;
          mpvFormat(int value) { this.value = value; }
          public int getValue() { return value; }
     }

     public static class mpvEventId {
          public static final int MPV_EVENT_NONE=0;
          public static final int MPV_EVENT_SHUTDOWN=1;
          public static final int MPV_EVENT_LOG_MESSAGE=2;
          public static final int MPV_EVENT_GET_PROPERTY_REPLY=3;
          public static final int MPV_EVENT_SET_PROPERTY_REPLY=4;
          public static final int MPV_EVENT_COMMAND_REPLY=5;
          public static final int MPV_EVENT_START_FILE=6;
          public static final int MPV_EVENT_END_FILE=7;
          public static final int MPV_EVENT_FILE_LOADED=8;
          public static final int MPV_EVENT_TRACKS_CHANGED=9;
          public static final int MPV_EVENT_TRACK_SWITCHED=10;
          public static final int MPV_EVENT_IDLE=11;
          public static final int MPV_EVENT_PAUSE=12;
          public static final int MPV_EVENT_UNPAUSE=13;
          public static final int MPV_EVENT_TICK=14;
          public static final int MPV_EVENT_SCRIPT_INPUT_DISPATCH=15;
          public static final int MPV_EVENT_CLIENT_MESSAGE=16;
          public static final int MPV_EVENT_VIDEO_RECONFIG=17;
          public static final int MPV_EVENT_AUDIO_RECONFIG=18;
          public static final int MPV_EVENT_METADATA_UPDATE=19;
          public static final int MPV_EVENT_SEEK=20;
          public static final int MPV_EVENT_PLAYBACK_RESTART=21;
          public static final int MPV_EVENT_PROPERTY_CHANGE=22;
          public static final int MPV_EVENT_CHAPTER_CHANGE=23;
          public static final int MPV_EVENT_QUEUE_OVERFLOW=24;
     }
}
