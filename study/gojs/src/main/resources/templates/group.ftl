<!DOCTYPE html>
<html>
<head>
<#assign title = "组" />
<#include "/common/head.ftl">
<script id="code">
  function init() {
    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
    var $ = go.GraphObject.make;

    myDiagram =
      $(go.Diagram, "myDiagramDiv",
        {
          initialContentAlignment: go.Spot.Center,
          layout: $(go.TreeLayout, { setsPortSpot: false, setsChildPortSpot: false })
        });

    myDiagram.nodeTemplate =
      $(go.Node, "Vertical",
        { defaultStretch: go.GraphObject.Horizontal },
        { fromSpot: go.Spot.RightSide, toSpot: go.Spot.LeftSide },
        $(go.Panel, "Auto",
          $(go.Shape, "RoundedTopRectangle",
            { fill: "white" },
            new go.Binding("fill", "role", function(r) { return r[0] === 't' ? "lightgray" : "white"; })),
          $(go.TextBlock,
            { margin: new go.Margin(2, 2, 0, 2), textAlign: "center" },
            new go.Binding("text", "header"))
        ),
        $(go.Panel, "Auto",
          { minSize: new go.Size(NaN, 70) },
          $(go.Shape, "Rectangle", { fill: "white" }),
          $(go.TextBlock,
            { width: 120 },
            { margin: new go.Margin(2, 2, 0, 2), textAlign: "center" },
            new go.Binding("text", "text")),
          $(go.Shape, "BpmnActivityLoop",
            {
              visible: false, width: 10, height: 10,
              alignment: new go.Spot(0.5, 1, 0, -3), alignmentFocus: go.Spot.Bottom
            },
            new go.Binding("visible", "loop"))
        ),
        $(go.Panel, "Auto",
          $(go.Shape, "RoundedBottomRectangle",
            { fill: "white" },
            new go.Binding("fill", "role", function(r) { return r[0] === 'b' ? "lightgray" : "white"; })),
          $(go.TextBlock,
            { margin: new go.Margin(2, 2, 0, 2), textAlign: "center" },
            new go.Binding("text", "footer"))
        )
      );

    myDiagram.groupTemplate =
      $(go.Group, "Vertical",
        { layout: $(go.TreeLayout, { setsPortSpot: false, setsChildPortSpot: false }) },
        { defaultStretch: go.GraphObject.Horizontal },
        { fromSpot: go.Spot.RightSide, toSpot: go.Spot.LeftSide },
        $(go.Panel, "Auto",
          $(go.Shape, "RoundedTopRectangle",
            { fill: "white" },
            new go.Binding("fill", "role", function(r) { return r[0] === 't' ? "lightgray" : "white"; })),
          $(go.TextBlock,
            { margin: new go.Margin(2, 2, 0, 2), textAlign: "center" },
            new go.Binding("text", "header"))
        ),
        $(go.Panel, "Auto",
          $(go.Shape, { fill: "white" }),
          $(go.Placeholder, { padding: 20 }),
          $(go.Shape, "BpmnActivityLoop",
            {
              visible: false, width: 10, height: 10,
              alignment: new go.Spot(0.5, 1, 0, -3), alignmentFocus: go.Spot.Bottom
            },
            new go.Binding("visible", "loop"))
        ),
        $(go.Panel, "Auto",
          $(go.Shape, "RoundedBottomRectangle",
            { fill: "white" },
            new go.Binding("fill", "role", function(r) { return r[0] === 'b' ? "lightgray" : "white"; })),
          $(go.TextBlock,
            { margin: new go.Margin(2, 2, 0, 2), textAlign: "center" },
            new go.Binding("text", "footer"))
        )
      );

    myDiagram.linkTemplate =
      $(go.Link,
        { routing: go.Link.Orthogonal, corner: 5 },
        $(go.Shape),
        $(go.Shape, { toArrow: "Triangle"})
      );

    myDiagram.model = new go.GraphLinksModel([
      { key: 1, header: "Supplier", text: "Planned Order Variations", footer: "Retailer", role: "b" },
      { key: 2, header: "Supplier", text: "Order & Delivery Variations", footer: "Retailer", role: "t", loop: true },
      { key: 3, header: "Supplier", isGroup: true, footer: "Shipper", role: "b" },
      { key: 4, header: "Supplier", text: "Planned Order Variations", footer: "Retailer", role: "b", group: 3 },
      { key: 5, header: "Supplier", text: "Order & Delivery Variations", footer: "Retailer", role: "t", group: 3 },
      { key: 13, header: "Supplier", isGroup: true, footer: "Shipper", role: "b", loop: true },
      { key: 14, header: "Supplier", text: "Planned Order Variations", footer: "Retailer", role: "b", group: 13 },
      { key: 15, header: "Supplier", text: "Order & Delivery Variations", footer: "Retailer", role: "t", group: 13 }
    ], [
      { from: 1, to: 2 },
      { from: 2, to: 3 },
      { from: 2, to: 13 },
      { from: 4, to: 5 },
      { from: 14, to: 15 }
    ]);
  }
</script>
</head>
<body onload="init()">
<div id="sample">
  <div id="myDiagramDiv" style="border: solid 1px black; width:100%; height:600px"></div>
  <p>
    Group demo.
  </p>
</div>
</body>
</html>