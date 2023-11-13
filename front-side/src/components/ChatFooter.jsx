import { useRef, useEffect } from "react";
import { Send } from "lucide-react";
import { Button } from "./ui/button";
import InputWithIcon from "./InputWithIcon";
import { useState } from "react";
import EmojiPicker from "emoji-picker-react";
import { io } from "socket.io-client";

let socket;

const ChatFooter = () => {
  const [value, setValue] = useState("");
  const [showEmojiPicker, setShowEmojiPicker] = useState(false);
  const fileUploadInputRef = useRef(null);

  useEffect(()=>{
    socketInitializer()
  }, [])

  const socketInitializer = async () => {
    await fetch('/api/socket')
    socket = io()

    socket.on('connect', () => {
      console.log('connected')
    })
  }


  const handleFileUpload = () => {
    if (fileUploadInputRef.current) {
      fileUploadInputRef.current.click();
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    e.preventDefault();
    const formdata = new FormData(e.target);
    const jsonData = Array.from(formdata.entries()).reduce(
      (acc, [key, value]) => {
        acc[key] = value;
        return acc;
      },
      {}
    );
    socket.emit("message", jsonData)
  };

  return (
    <div
      className={`flex sticky bg-white bottom-0 border-t border-t-slate-300 dark:border-0 z-10 w-full p-4`}
    >
      <form className="relative flex gap-2 w-full" onSubmit={handleSubmit}>
        <input
          type="file"
          className="hidden"
          name="images"
          ref={fileUploadInputRef}
        />
        <InputWithIcon
          startIcon="image"
          name="content"
          placeholder={"Write message..."}
          size={16}
          classnames={
            "rounded-md border-slate-200 bg-slate-200 dark:bg-transparent dark:border outline-none px-2 flex-1"
          }
          handleIconClick={handleFileUpload}
          endIcon="smile"
          endIconClick={() => {
            setShowEmojiPicker((prev) => !prev);
          }}
          value={value}
          onChange={(e) => {
            setValue(e.target.value);
          }}
        />
        {showEmojiPicker && (
          <div className="absolute right-2 bottom-12">
            <EmojiPicker
              onEmojiClick={(data, event) => {
                setValue((prev) => prev + data.emoji);
              }}
            />
          </div>
        )}

        <Button className="bg-blue-600 hover:bg-blue-800">
          <Send size={18} />
        </Button>
      </form>
    </div>
  );
};

export default ChatFooter;
