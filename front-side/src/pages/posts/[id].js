import { useEffect, useRef, useState } from "react";
import { ArrowBigDown, ArrowBigUp, MessageSquare, Flag, ThumbsDown, ThumbsUp } from "lucide-react";
import PostPageNavbar from "@/components/PostPageNavbar";
import { Editor } from "@tinymce/tinymce-react";
import { Button } from "@/components/ui/button";
import Comment from "@/components/Comment";
import { useToast } from "@/components/ui/use-toast";
import { useRouter } from "next/router";
import axiosRequest from "@/utils/axiosRequest";
import { useUserInfoStore } from "@/store/userInfoStore";
import TimeAgo from "timeago-react";

const SinglePostPage = () => {
  const [postData, setPostData] = useState(null);
  const [postComments, setPostComments] = useState([]);
  const [isLoadingComments, setIsLoadingComments] = useState(false);
  const { toast } = useToast();
  const editorRef = useRef();
  const router = useRouter();
  const userInfo = useUserInfoStore((state) => state.userInfo);

  const postId = router.query.id;

  const fetchComments = () => {
    axiosRequest
      .get(`/posts/${postId}/comments`)
      .then((res) => {
        setPostComments(res.data?.content);
      })
      .catch((err) => {
        toast({
          title: "Opps",
          variant: "destructive",
          description: err.message,
        });
      })
      .finally(() => setIsLoadingComments(false));
  };

  useEffect(() => {
    if (!postId) return;
    axiosRequest
      .get(`/posts/${postId}`)
      .then((res) => {
        if (res.status === 200) {
          setPostData(res.data);
        }
      })
      .catch((err) => {
        toast({
          title: "Opps Something went wrong",
          variant: "destructive",
          description: err.message,
        });
      });
    fetchComments();
  }, [postId]);

  // const handleScroll = () => {
  //   if (
  //     window.innerHeight + document.documentElement.scrollTop >=
  //       document.documentElement.offsetHeight - 200 &&
  //     !isLoadingComments &&
  //     page < totalPages
  //   ) {
  //     fetchData();
  //   }
  // };

  // useEffect(() => {
  //   window.addEventListener("scroll", handleScroll);
  //   return () => window.removeEventListener("scroll", handleScroll);
  // }, [isLoadingComments]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (editorRef.current) {
      if (!editorRef.current.getContent()) {
        toast({
          title: "OOps",
          description: "Please field cannot be empty!",
          variant: "destructive",
        });
        return;
      }
      const data = {
        content: editorRef.current.getContent(),
        authorId: userInfo.id,
        postId: postId,
      };
      axiosRequest
        .post("comments", data)
        .then((res) => {
          if (res.status === 200) {
            setIsLoadingComments(true);
          }
        })
        .catch((err) => {
          toast({
            title: "Opps",
            variant: "destructive",
            description: err.message,
          });
        });
    }
  };

  useEffect(() => {
    if (!isLoadingComments || !postId) return;
    fetchComments();
  }, [isLoadingComments]);

  return (
    <div className="bg-slate-200 min-h-screen">
      <PostPageNavbar disabledSearch />
      <main className="px-8 sm:px-52 py-10 flex gap-8">
        <div className="flex flex-col sm:flex-row gap-3 w-full bg-white rounded-sm border border-slate-300 pb-8">
          <div className="text-slate-500 flex flex-col items-center h-full bg-slate-50 p-2">
            <ThumbsUp className="cursor-pointer text-blue-500" />
            <span className="my-2 text-slate-700">23</span>
            <ThumbsDown className="cursor-pointer text-red-500" />
          </div>

          <div className="flex flex-col gap-2 py-2">
            <div className="flex items-center gap-2">
              <p className="font-semibold text-sm">r/programming</p>
              <span className="text-slate-400 text-xs">
                Posted by John Doe <TimeAgo datetime={postData?.createdAt} />
              </span>
            </div>

            <p className="text-md text-slate-800 font-semibold">
              {postData?.title}
            </p>
            <div>
              <div
                className="text-sm text-slate-600 font-light"
                dangerouslySetInnerHTML={{ __html: postData?.content }}
              ></div>
            </div>

            <form
              className="border-b border-b-slate-200 py-4 grid gap-4"
              onSubmit={handleSubmit}
            >
              <span className="text-slate-500 gap-4 font-semibold">
                Enter your comment here
              </span>
              <Editor
                apiKey={process.env.NEXT_PUBLIC_TINYMCE_KEY}
                onInit={(event, editor) => (editorRef.current = editor)}
                init={{
                  height: 200,
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
              <Button
                variant="outline"
                type="submit"
                className="bg-slate-200 w-max text-slate-500 text-sm rounded-full"
              >
                Comment
              </Button>
            </form>

            {postComments?.length < 1 ? <p>Be the first to comment</p> : null}

            {postComments?.map((e, i) => (
              <Comment key={i} data={e} />
            ))}
          </div>
        </div>
      </main>
    </div>
  );
};

export default SinglePostPage;
