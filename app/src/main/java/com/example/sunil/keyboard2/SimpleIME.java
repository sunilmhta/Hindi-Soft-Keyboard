package com.example.sunil.keyboard2;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class SimpleIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {


    private KeyboardView hindikeyboardview; //referencing the view defined in the layout
    private Keyboard keyboard; //instance that is assigned to the KeyboardView


//This function is first called when the keyboard  starts
    @Override
    public View onCreateInputView()
    {
        hindikeyboardview = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty); // setting the view to be qwerty.xml for the first time
        hindikeyboardview.setKeyboard(keyboard);
        hindikeyboardview.setOnKeyboardActionListener(this);
        return hindikeyboardview;
    }

    //this function is to play sound when any button is tapped on the keyboard
    private void playClick(int keyCode)
    {
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode)
        {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

//this function is called when any button is tapped on the keyboard
    @Override
    public void onKey(int primaryCode, int[] keyCodes)
    {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode); //calling the function to play sound.
        switch(primaryCode)
        {
            case Keyboard.KEYCODE_DELETE : // when delete button is tapped
                ic.deleteSurroundingText(1, 0);
                break;
            case -123: // when the hindi numeral button is tapped
                keyboard = new Keyboard(this, R.xml.hindi_number);
                hindikeyboardview.setKeyboard(keyboard);
                hindikeyboardview.setOnKeyboardActionListener(this);
                break;
            case -1234:// when the english numeral button is tapped
                keyboard = new Keyboard(this, R.xml.english_number);
                hindikeyboardview.setKeyboard(keyboard);
                hindikeyboardview.setOnKeyboardActionListener(this);
                break;
            case -456:// default layout of the keyboard
                keyboard = new Keyboard(this, R.xml.qwerty);
                hindikeyboardview.setKeyboard(keyboard);
                hindikeyboardview.setOnKeyboardActionListener(this);
                break;
            case -789:// when the english numeral button is tapped
                keyboard = new Keyboard(this, R.xml.kshatra);
                hindikeyboardview.setKeyboard(keyboard);
                hindikeyboardview.setOnKeyboardActionListener(this);
                break;
            case Keyboard.KEYCODE_DONE://when enter key(OK Key) is pressed
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case 1:
                ic.commitText("क्ष",1);
                break;
            case 2:
                ic.commitText("त्र",1);
                break;
            case 3:
                ic.commitText("ज्ञ",1);
                break;
            case 4:
                ic.commitText("श्र",1);
                break;
            case 5:
                ic.commitText("क्र",1);
                break;
            case 6:
                ic.commitText("द्र",1);
                break;
            case 7:
                ic.commitText("प्र",1);
                break;
            case 8:
                ic.commitText("ट्र",1);
                break;
            case 9:
                ic.commitText("क्त",1);
                break;
            case 10:
                ic.commitText("स्थ",1);
                break;
            case 11:
                ic.commitText("स्त",1);
                break;
            case 12:
                ic.commitText("स्ट",1);
                break;
            case 13:
                ic.commitText("स्व",1);
                break;
            case 14:
                ic.commitText("व्य",1);
                break;
            case 15:
                ic.commitText("ध्य",1);
                break;
            case 16:
                ic.commitText("न्य",1);
                break;
            case 17:
                ic.commitText("न्ह",1);
                break;
            case 18:
                ic.commitText("र्म",1);
                break;
            case 19:
                ic.commitText("र्य",1);
                break;
            default:
                char code  =  (char)primaryCode; //converting the integer code to a character i.e a letter
                ic.commitText(String.valueOf(code),1); // put the letter on the input text area
        }
    }


    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }
}