
export class DomOps {
    public static getTagByTypeAndName(tagType : string, tagNameAttributeValue : string) : any {
        var links = document.getElementsByTagName(tagType);

        for(var l = 0 ; l < links.length ; l++) {
            var link = links[l];
            if (link.getAttribute("name") == tagNameAttributeValue) {
                return link;
            }
        }
    }
}
