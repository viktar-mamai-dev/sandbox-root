package com.mamay.leetcode.practice.medium1;

import java.util.HashMap;

class FrequencyTracker {
    private final HashMap<Integer, Integer> valToFreq, freqToFreq;

    public FrequencyTracker() {
        valToFreq = new HashMap<>();
        freqToFreq = new HashMap<>();
    }

    public void add(int number) {
        int freq = valToFreq.getOrDefault(number, 0) + 1;
        valToFreq.put(number, freq);
        if (freq > 1) {
            freqToFreq.put(freq - 1, freqToFreq.get(freq - 1) - 1);
        }
        freqToFreq.put(freq, freqToFreq.getOrDefault(freq, 0) + 1);
    }

    public void deleteOne(int number) {
        if (!valToFreq.containsKey(number)) return;
        int freq = valToFreq.get(number) - 1;
        valToFreq.put(number, freq);
        if (freq > 0) {
            freqToFreq.put(freq, freqToFreq.getOrDefault(freq, 0) + 1);
        }
        freqToFreq.put(freq + 1, freqToFreq.get(freq + 1) - 1);
    }

    public boolean hasFrequency(int frequency) {
        Integer freqOfFreq = freqToFreq.get(frequency);
        return freqOfFreq != null && freqOfFreq > 0;
    }
}
/*
["FrequencyTracker","deleteOne","hasFrequency","hasFrequency","deleteOne","hasFrequency","hasFrequency",
"add","deleteOne","deleteOne"]
[[],[5],[1],[1],[3],[1],[1],[7],[7],[7]]
 */
class FrequencyRunner {
    public static void main(String[] args) {
        FrequencyTracker tracker = new FrequencyTracker();
        tracker.deleteOne(5);
        tracker.hasFrequency(1);
        tracker.hasFrequency(1);
        tracker.deleteOne(3);
        tracker.hasFrequency(1);
        tracker.hasFrequency(1);
        tracker.add(7);
        tracker.deleteOne(3);
        tracker.deleteOne(3);
    }
}
