import {
    Popover,
    PopoverContent,
    PopoverTrigger,
  } from "@/components/ui/popover";
  
  export function CustomPopover({ triggerButton, body }) {
    return (
      <Popover>
        <PopoverTrigger asChild>{triggerButton}</PopoverTrigger>
        <PopoverContent className="w-80">{body}</PopoverContent>
      </Popover>
    );
  }
  