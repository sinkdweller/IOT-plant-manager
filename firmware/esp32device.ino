#include <WiFiManager.h>
#include <Preferences.h>
//for file writing

void setup() {
    WiFiManager wifiManager;
    Serial.begin(115200);
    Preferences preferences;
    preferences.begin("iot-app", false); //enalb e read
    
    String savedIP = preferences.getString("server_ip", "192.168.1.1"); //placeholder ip
        
    //Add IP address input field
    WiFiManagerParameter local_IP_address("ip_address", "enter your ip", savedIP.c_str(), 15);
    wifiManager.addParameter(&local_IP_address);
    
    // stop and wait to connect via phone if it can't find Wi-Fi, stored in flash
    bool res = wifiManager.autoConnect("ESP32_Setup_Portal"); 

    if(!res) {
        Serial.println("Failed to connect");
    } else {
        Serial.println("Connected to Wi-Fi! Proceeding to main code...");
        if(String(local_IP_address.getValue())!= savedIP){ //fixed char to string comparison mismatch
          //commit to memory 
          preferences.putString("server_ip", local_IP_address.getValue());
          Serial.println("Saved new IP server address.");

        }
    }
    preferences.end(); // Clean up memory
}
void loop(){}