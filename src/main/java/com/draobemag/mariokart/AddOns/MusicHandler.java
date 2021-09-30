package com.draobemag.mariokart.AddOns;

import com.draobemag.mariokart.HelloApplication;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.SysexMessage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MusicHandler {
    private static MusicHandler instance = null;

    private Sequencer sequencer;
    private File currentSong;
    private InputStream is;
    private Receiver receiver;
    private boolean muted;

    private MusicHandler() throws MidiUnavailableException {
        muted = false;
        sequencer = MidiSystem.getSequencer();
        currentSong = null;
        is = null;
        receiver = null;
    }

    public static MusicHandler MusicHandler() throws MidiUnavailableException, IOException,
            InvalidMidiDataException
    {
        if (instance == null)
        {
            instance = new MusicHandler();
        }

        return instance;
    }

    public void setCurrentSong(String songPath)
    {
        URL temp = HelloApplication.class.getResource(songPath);
        System.out.println(temp);
        String path = temp.getFile();
        System.out.println(path);
        currentSong = new File(path);
    }

    public void play() throws MidiUnavailableException, InvalidMidiDataException, IOException {
        sequencer.open();
        InputStream is = new BufferedInputStream(new FileInputStream(currentSong));
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        sequencer.setSequence(is);
        sequencer.start();

        receiver = sequencer.getTransmitters().iterator().next().getReceiver();

        Double gain = 0.15D * 127;
        byte volumeByte = (byte) gain.intValue();
        MidiMessage volumeMessage = new SysexMessage(SysexMessage.SYSTEM_EXCLUSIVE,
                new byte[]{0x7F, 0x7F, 0x04, 0x01, 0x00, volumeByte}, 6);
        receiver.send(volumeMessage, -1);
    }

    /**
     * Closes the Music player
     * Call before the stage closes.
     */
    public void close() {
        sequencer.close();
    }

    /**
     * Toggles Mute
     *
     * @throws InvalidMidiDataException if the midi message is invalid
     */
    public void toggleMute() throws InvalidMidiDataException {
        // gain is a value between 0 and 1 (loudest)
        Double gain = (muted) ? 0.15D * 127 : 0.0D * 127;
        byte volumeByte = (byte) gain.intValue();

        MidiMessage volumeMessage = new SysexMessage(SysexMessage.SYSTEM_EXCLUSIVE,
                new byte[]{0x7F, 0x7F, 0x04, 0x01, 0x00, volumeByte}, 6);
        receiver.send(volumeMessage, -1);

        muted ^= true;
    }
}
