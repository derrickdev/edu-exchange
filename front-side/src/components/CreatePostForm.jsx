"use client";

import React, { useState } from "react";
import axiosRequest from "@/utils/axiosRequest";
import { Button } from "./ui/button";
import { Editor } from "@tinymce/tinymce-react";
import { useToast } from "./ui/use-toast";
import { useUserInfoStore } from "@/store/userInfoStore";
import globalStore from "@/store/globalStore";
import InputWithLabel from "./InputWithLabel";
import TopicsList from "./TopicsList";

const CreatePostForm = ({ closeModal }) => {
  const { toast } = useToast();
  const userInfo = useUserInfoStore((state) => state.userInfo);
  const setShouldRefetch = globalStore((state) => state.setShouldRefetchData);
  const [topics, setTopics] = useState([""]);
  const [postTitle, setPostTitle] = useState("");
  const editorRef = React.useRef();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (true) {
      if (editorRef.current) {
        if (!editorRef.current.getContent()) {
          toast({
            title: "OOps",
            description: "Please your post content cannot be empty!!",
            variant: "destructive",
          });
          return;
        }
        const data = {
          content: editorRef.current.getContent(),
          title: postTitle,
          userId: userInfo.id,
          topicNames: topics,
        };
        axiosRequest
          .post("/posts", data)
          .then((response) => {
            setShouldRefetch(true);
            toast({
              title: "Great!",
              description: "Post created successfully!",
            });
          })
          .catch((error) => {
            toast({
              title: "Oops, something went wrong",
              description: error.message,
              variant: "destructive",
            });
          })
          .finally(() => closeModal());
      }
    }
  };

  return (
    <form className="grid gap-4" onSubmit={handleSubmit}>
      <TopicsList setSelectedTopics={setTopics} />
      <InputWithLabel
        type="text"
        label="Choose a title for your post"
        className="block w-full rounded-md border-0 py-1.5 px-3 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 border-none focus:ring-1 focus:outline-gray-500 sm:text-sm sm:leading-6"
        name="title"
        placeholder="When is Musk sending us to Mars??"
        value={postTitle}
        onChange={(e) => setPostTitle(e.target.value)}
        required
      />
      <Editor
        apiKey={process.env.NEXT_PUBLIC_TINYMCE_KEY}
        onInit={(event, editor) => (editorRef.current = editor)}
        init={{
          height: 300,
          menubar: false,
          plugins: [
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime media table code help wordcount",
          ],
          toolbar:
            "undo redo | formatselect | " +
            "bold italic backcolor | alignleft aligncenter " +
            "alignright alignjustify | bullist numlist outdent indent | " +
            "removeformat | help",
          content_style:
            "body { font-family:Helvetica,Arial,sans-serif; font-size:14px }",
        }}
      />
      <div>
        <Button
          type="submit"
          size="icon"
          className="flex justify-center items-center gap-2 rounded-sm px-10"
        >
          Save
        </Button>
      </div>
    </form>
  );
};

export default CreatePostForm;
