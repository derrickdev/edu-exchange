import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";

const PostRuleCard = () => {
  return (
    <div className="flex flex-col w-full bg-white rounded-sm border border-slate-300">
      <div className="w-full p-3 bg-red-400 text-sm text-white font-semibold flex items-center rounded-t-sm">
        Rules
      </div>
      <Accordion type="single" collapsible className="w-full px-4 text-sm text-slate-700">
        <AccordionItem value="item-1">
          <AccordionTrigger className="text-left font-semibold">
            1. No hate-speech, personal attacks, or harassment
          </AccordionTrigger>
          <AccordionContent>
            This is a positive community. Any bashing, hateful attacks, or
            sexist remarks will be removed. You may also be permanently banned.
            You can state your opinion in a constructive manner.
          </AccordionContent>
        </AccordionItem>
        <AccordionItem value="item-2">
          <AccordionTrigger className="text-left">
            2. Questions and help requests must be a text post
          </AccordionTrigger>
          <AccordionContent>
            When you're asking a question or starting a discussion you need to
            make a "self" or text post. This keeps your question up at the top
            where people can read it, instead of it getting lost in replies or
            discussion.
          </AccordionContent>
        </AccordionItem>
        <AccordionItem value="item-3">
          <AccordionTrigger className="text-left">
            3. Keep submissions on topic and of high quality
          </AccordionTrigger>
          <AccordionContent>
            Submissions should be directly related to programming. Just because
            it has a computer in it doesn't make it programming. Submissions
            containing no real content that are simply farming for e-mail
            addresses will be removed as spam. Direct links to app demos
            (unrelated to programming) will be removed. Please link to a blog
            post/post-mortem about the development process instead.
          </AccordionContent>
        </AccordionItem>
        <AccordionItem value="item-4">
          <AccordionTrigger className="text-left">
            4. Please don't downvote without commenting your reasoning for doing
            so
          </AccordionTrigger>
          <AccordionContent>
            Obviously we can't enforce this one very easily, it more is a level
            of trust we have in our users. Please do not downvote comments
            without providing valid reasoning for doing so. This rule helps
            maintain a positive atmosphere on the subreddit with both posts and
            comments.
          </AccordionContent>
        </AccordionItem>
        <AccordionItem value="item-5">
          <AccordionTrigger className="text-left">
            5. No memes, image macros, rage comics, overdone jokes
          </AccordionTrigger>
          <AccordionContent>
            Meme posts of any kind are not allowed. This rule can also apply to
            comments, including overdone jokes, comment-chain jokes, or other
            redditisms that are popular elsewhere.
          </AccordionContent>
        </AccordionItem>
      </Accordion>
    </div>
  );
};

export default PostRuleCard;
